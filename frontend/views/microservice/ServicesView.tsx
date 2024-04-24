import { JSXElementConstructor, Key, ReactElement, ReactFragment, ReactPortal, useEffect, useState} from 'react';
// @ts-ignore
import BpmnModeler from 'bpmn-js/dist/bpmn-modeler.production.min.js';
import 'bpmn-js/dist/assets/diagram-js.css';
import axios from 'axios';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';

export default function BpmnEditor() {
    const [bpmnModeler, setBpmnModeler] = useState<BpmnModeler | null>(null);
    const [fileList, setFileList] = useState([]);

    useEffect(() => {
        const initBpmnModeler = async () => {
            const modeler = new BpmnModeler({
                container: '#bpmn-container',
                keyboard: {
                    bindTo: document
                }
            });

            setBpmnModeler(modeler);
        };

        initBpmnModeler();
    }, []);

    useEffect(() => {
        fetchDiagramList();
    }, []);

    const fetchDiagramList = async () => {
        try {
            const response = await axios.get('/list-diagrams');
            setFileList(response.data);
        } catch (error) {
            console.error('Failed to fetch diagrams:', error);
        }
    };

    const createNewDiagram = async () => {
        await bpmnModeler?.createDiagram();
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
                } catch (error) {
                    console.error('Error while saving diagram in backend:', error);
                }
            } else {
                console.log('Save cancelled.');
            }
        }
    };

    const deployDiagram = async () => {
        const result = await bpmnModeler?.saveXML({format: true});

        if (result && result.xml) {
            try {
                await axios.post('/deploy-diagram', {xml: result.xml});
                console.log('Diagram deployed to backend.');
            } catch (error) {
                console.error('Error during deploying diagram to backend:', error);
            }
        }
    };

    const deleteDiagram = async (filename: any) => {
        try {
            await axios.delete(`/delete-diagram/${filename}`);
            console.log('Diagram deleted successfully.');
            fetchDiagramList();  // Refresh list after deleting a diagram
        } catch (error) {
            console.error('Failed to delete diagram:', error);
        }
    };

    const loadDiagram = async (filename: any) => {
        try {
            const response = await axios.get(`/get-diagram/${filename}`);
            if (bpmnModeler) {
                bpmnModeler.importXML(response.data);
                console.log('Diagram loaded into modeler.');
            }
        } catch (error) {
            console.error('Failed to load diagram:', error);
        }
    };

    const simulateDiagram = async (filename: any) => {
        console.log('To the simulation!');
    }

    return (
        <div>
            <div id="bpmn-container" style={{height: "600px", border: "solid 0.1px", margin: "15px"}}></div>
            <button style={{margin: "15px"}} onClick={createNewDiagram}>Create New Diagram</button>
            <button style={{margin: "15px"}} onClick={saveDiagram}>Save Diagram</button>
            <h1 style={{margin: "15px"}} >List of BPMN Models</h1>
            <ul>
                {fileList.map((file: string | number | boolean | ReactElement<any, string | JSXElementConstructor<any>> | ReactFragment | ReactPortal | null | undefined, index: Key | null | undefined) => (
                    <li key={index}>{file} <button style={{margin: "15px"}} onClick={() => loadDiagram(file)}>Load Diagram</button> <button style={{margin: "15px"}}onClick={deployDiagram}>Deploy Diagram</button>
                        <button style={{margin: "15px"}} onClick={() => deleteDiagram(file)}>Delete Diagram</button>
                        <button style={{margin: "15px"}} onClick={() => simulateDiagram(file)}>Simulate Diagram</button></li>
                ))}
            </ul>
        </div>
    );
}
