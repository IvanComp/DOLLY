import { JSXElementConstructor, Key, ReactElement, ReactFragment, ReactPortal, useEffect, useState} from 'react';
// @ts-ignore
import BpmnModeler from 'bpmn-js/dist/bpmn-modeler.production.min.js';
import 'bpmn-js/dist/assets/diagram-js.css';
import './fileList.css';
import axios from 'axios';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import {BsDiagram2} from "react-icons/bs";
import {Link} from "react-router-dom";
import { Notyf } from 'notyf';
import 'notyf/notyf.min.css'

export default function BpmnEditor() {
    const [bpmnModeler, setBpmnModeler] = useState<BpmnModeler | null>(null);
    const [fileList, setFileList] = useState([]);
    const [showModeler, setShowModeler] = useState(false); // New state to manage visibility
    const notyf = new Notyf({
        duration: 3000, // Durata predefinita per tutte le notifiche (in millisecondi)
        ripple: false, // Abilita l'effetto ondulato al click
        position: {
            x: 'right',
            y: 'top',
        },
        types: [
            {
                type: 'warning',
                background: 'orange',
                icon: {
                    className: 'material-icons',
                    tagName: 'i',
                    text: 'warning'
                }
            },
            {
                type: 'error',
                background: 'indianred',
                duration: 2000,
                dismissible: true
            }
        ]
    });

    useEffect(() => {
        if (showModeler && !bpmnModeler) {
            const modeler = new BpmnModeler({
                container: '#bpmn-container',
                keyboard: {
                    bindTo: document
                }
            });
            setBpmnModeler(modeler);
        }
    }, [showModeler, bpmnModeler]);

    useEffect(() => {
        fetchDiagramList();
    }, []);


    const fetchDiagramList = async () => {
        try {
            const response = await axios.get('/list-diagrams');
            setFileList(response.data);
        } catch (error) {
            console.error('Failed to fetch diagrams:', error);
            notyf.error('Failed to load diagram list!');
        }
    };

    const createNewDiagram = async () => {
        setShowModeler(true);
        if (bpmnModeler) {
            bpmnModeler.createDiagram();
            notyf.success('Starting the creation of the BPMN model...');
        }
    };

    const saveDiagram = async () => {
        const result = await bpmnModeler?.saveXML({format: true});
        if (result && result.xml) {
            const filename = window.prompt("Please enter the filename to save your diagram:", "myDiagram");
            if (filename) {
                try {
                    await axios.post('/save-diagram', {
                        xml: result.xml,
                        filename: filename
                    }, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    console.log('Diagram saved in backend.');
                    fetchDiagramList();
                    notyf.success('Diagram saved successfully!');
                } catch (error) {
                    console.error('Error while saving diagram in backend:', error);
                    notyf.error('Error while saving diagram in backend!');
                }
            } else {
                console.log('Save cancelled.');
                notyf.error('Save cancelled!');
            }
        }
    };

    const deployDiagram = async () => {
        const result = await bpmnModeler?.saveXML({format: true});

        if (result && result.xml) {
            try {
                await axios.post('/deploy-diagram', {xml: result.xml});
                console.log('Diagram deployed to backend.');
                notyf.success({
                    type: 'warning',
                    message: 'Starting the Deploy of the BPMN model...'
                });
            } catch (error) {
                console.error('Error during deploying diagram to backend:', error);
                notyf.error('Error during deploying diagram to backend!');
            }
        }
    };

    const deleteDiagram = async (filename: any) => {
        try {
            await axios.delete(`/delete-diagram/${filename}`);
            console.log('Diagram deleted successfully.');
            notyf.success('Diagram deleted successfully!');
            fetchDiagramList();  // Refresh list after deleting a diagram
        } catch (error) {
            console.error('Failed to delete diagram:', error);
            notyf.error('Failed to delete diagram!');
        }
    };

    const loadDiagram = async (filename: any) => {
        try {
            const response = await axios.get(`/get-diagram/${filename}`);
            if (bpmnModeler) {
                bpmnModeler.importXML(response.data);
                console.log('Diagram loaded into modeler.');
                notyf.success('Diagram loaded successfully!');
            }
        } catch (error) {
            console.error('Failed to load diagram:', error);
            notyf.error('Failed to load diagram!');
        }
    };

    const simulateDiagram = async (filename: any) => {
        console.log('To the simulation!');
        notyf.success({
            type: 'warning',
            message: 'Starting the Simulation of the BPMN model...'
        });
    }

    return (
        <div>

            <h1 style={{margin: "15px"}} >List of BPMN Models</h1>
            <div style={{ border: "2px solid rgba(0, 0, 0, 0.05)", margin: "15px", padding: "1px", borderRadius: "5px", marginBottom: "1px", fontSize: "15px", color: "black" }}>
                {fileList.map((file: string | number | boolean | ReactElement<any, string | JSXElementConstructor<any>> | ReactFragment | ReactPortal | null | undefined, index: Key | null | undefined) => (
                    <div className="file-info" key={index}>
                        <p className="file-info-item-name file-name">
                            <BsDiagram2 /> {file}
                        </p>
                        <p className="file-info-item file-name">
                            <button style={{margin: "15px"}} onClick={() => loadDiagram(file)}>Load Diagram</button>
                        </p>
                        <p className="file-info-item file-name">
                            <Link to="/monitoring">
                                <button style={{margin: "15px"}} onClick={deployDiagram}>Deploy Diagram</button>
                            </Link>
                        </p>
                        <p className="file-info-item file-name">
                            <button style={{margin: "15px"}} onClick={() => deleteDiagram(file)}>Delete Diagram</button>
                        </p>
                        <p className="file-info-item file-name">
                            <Link to="/simulation">
                                <button style={{margin: "15px"}} onClick={() => simulateDiagram(file)}>Simulate Diagram</button>
                            </Link>
                        </p>
                    </div>
                ))}
            </div>

            <button style={{margin:'15px', background:'#324e6c', color: 'white', fontSize: '15px', padding: '10px 30px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '2%', marginBottom:'0.42cm'}} onClick={createNewDiagram}>Create New Diagram</button>
            <button style={{margin:'15px', background:'#324e6c', color: 'white', fontSize: '15px', padding: '10px 30px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '2%', marginBottom:'0.42cm'}} onClick={saveDiagram}>Save Current Diagram</button>

            {showModeler && (
                <div id="bpmn-container" style={{height: "600px", border: "solid 0.1px", margin: "15px"}}></div>
            )}

        </div>
    );
}
