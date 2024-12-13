import React, { JSXElementConstructor, Key, ReactElement, ReactFragment, ReactPortal, useEffect, useState } from 'react';
// @ts-ignore
import BpmnModeler from 'bpmn-js/dist/bpmn-modeler.production.min.js';
import 'bpmn-js/dist/assets/diagram-js.css';
import './fileList.css';
import axios from 'axios';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import { BsDiagram2 } from "react-icons/bs";
import { MdFileUpload } from "react-icons/md";
import { MdDeleteForever } from "react-icons/md";
import { MdPlayCircleOutline } from "react-icons/md";
import { MdAutoGraph } from "react-icons/md";
import { Link } from "react-router-dom";
import { Notyf } from 'notyf';
import 'notyf/notyf.min.css';
import Swal from 'sweetalert2';
// @ts-ignore
import {BpmnPropertiesPanelModule, BpmnPropertiesProviderModule } from 'bpmn-js-properties-panel';
import { useNavigate } from 'react-router-dom';
import TokenSimulationModule from 'bpmn-js-token-simulation';

interface Device {
    id: any;
    name: any;
    state: any;
}

export default function BpmnEditor() {
    const [bpmnModeler, setBpmnModeler] = useState<BpmnModeler | null>(null);
    const [fileList, setFileList] = useState<string[]>([]);
    const [showModeler, setShowModeler] = useState(false); // New state to manage visibility
    const [deviceArray, setDeviceArray] = useState<Device[]>([]);

    useEffect(() => {
        fetchData();
        fetchDiagramList();
        const fetchDevice = async () => {
            try {
                const response = await axios.get('http://localhost:4567/platform', {
                    headers: {
                        'Content-Type': 'text/plain'
                    },
                });
                console.log(JSON.stringify(response.data));
                setDeviceArray(response.data);
            } catch (error) {
                console.error('Errore durante il recupero dei dati del Device:', error);
            }
        };
        fetchDevice();
    }, []); // Chiamata una sola volta al caricamento del componente

    const fetchData = async () => {
        try {
            const deviceResponse = await getDevice();
            setDeviceArray(deviceResponse);

        } catch (error) {
            console.error('Errore durante il recupero dei dati:', error);
        }
    };

    const getDevice = async (): Promise<Device[]> => {
        try {
            const response = await axios.get('http://localhost:4567/device', {
                headers: {
                    'Content-Type': 'text/plain'
                },
            });

            console.log(JSON.stringify(response.data));
            return response.data; // Ritorna direttamente i dati piuttosto che inserirli in un array
        } catch (error) {
            console.error('Errore durante il recupero dei dati:', error);
            throw error;
        }
    };

    const notyf = new Notyf({duration: 3000, ripple: false,
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
                dismissible: false
            }
        ]
    });

    useEffect(() => {
        const modeler = new BpmnModeler({
            container: '#bpmn-container',
            keyboard: {
                bindTo: document
            },
            additionalModules: [
                BpmnPropertiesPanelModule,
                BpmnPropertiesProviderModule,
                TokenSimulationModule
            ]
        });
        setBpmnModeler(modeler);
        modeler.createDiagram();
        notyf.success('Starting the creation of the BPMN model...');
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

    const importNewDiagram = async (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        const file = event.target.files?.[0];
        if (!file) {
            notyf.error('No file selected!');
            return;
        }
    
        if (!file.name.endsWith('.bpmn')) { // Controlla l'estensione
            notyf.error('Invalid file format! Only .bpmn files are allowed.');
            return;
        }
    
        try {
            const fileContent = await file.text(); // Leggi il contenuto del file
            const fileName = file.name;
    
            // Simula un salvataggio nel backend
            await axios.post('/save-diagram', { 
                xml: fileContent,
                filename: fileName
            }, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
    
            setFileList((prev) => [...prev, fileName]); // Aggiorna la lista dei file
            notyf.success('Diagram imported successfully!');
        } catch (error) {
            console.error('Error importing diagram:', error);
            notyf.error('Error importing diagram!');
        }
    };

    const saveDiagram = async () => {
        const result = await bpmnModeler?.saveXML({format: true});
        if (result && result.xml) {

            Swal.fire({
                title: 'Enter the filename to save your BPMN Model',
                input: 'text',
                inputValue: 'myDiagram',
                showCancelButton: true,
                confirmButtonText: 'Save',
                cancelButtonText: 'Cancel',
                // @ts-ignore
                inputValidator: (value) => {
                    if (!value) {
                        return 'You need to write something!';
                    }
                }
            }).then(async (filename) => {
                if (filename.value) {
                    try {
                        await axios.post('/save-diagram', {
                            xml: result.xml,
                            filename: filename.value
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
            });
        }
    };
    const deleteDiagram = async (filename: any) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "Do you want to delete this BPMN model?",
            icon: 'warning',
            showDenyButton: true,
            confirmButtonText: 'Yes, delete it!',
            denyButtonText: 'No, cancel!',
            cancelButtonText: 'Cancel',
        }).then((result) => {
            if (result.isConfirmed) {
                axios.delete(`/delete-diagram/${filename}`).then(() => {
                    console.log('Diagram deleted successfully.');
                    notyf.success('Diagram deleted successfully!');
                    fetchDiagramList();
                }).catch((error) => {
                    console.error('Failed to delete diagram:', error);
                    notyf.error('Failed to delete diagram!');
                });
            } else if (result.isDenied) {
                console.log('Deletion cancelled.');
                notyf.error('Deletion cancelled!');
            }
        });
    };
    const loadDiagram = async (filename: any) => {
        Swal.fire({
            title: 'Are you sure?',
            text: `Do you want to load the BPMN model named "${filename}"?`,
            icon: 'question',
            showDenyButton: true,
            confirmButtonText: 'Yes, load it!',
            denyButtonText: 'No, cancel!',
            cancelButtonText: 'Cancel',
        }).then(async (result) => {
            if (result.isConfirmed) {
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
            } else if (result.isDismissed) {
                console.log('Loading cancelled.');
                notyf.error('Loading cancelled!');
            }
        });
    };
    const simulateDiagram = async (filename: any) => {
        Swal.fire({
            title: 'Are you sure?',
            text: `Do you want to start the simulation for the BPMN model named "${filename}"?`,
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Yes, start simulation',
            cancelButtonText: 'No, cancel',
        }).then(async (result) => {
            if (result.isConfirmed) {
                console.log('To the simulation!');
                notyf.success({
                    type: 'warning',
                    message: 'Starting the Simulation of the BPMN model...'
                });
                // Add any logic here to actually perform the simulation
                // For example, you might call a function to simulate or display the model dynamically
            } else if (result.isDismissed) {
                console.log('Simulation cancelled.');
                notyf.error('Simulation cancelled!');
            }
        });
    };
    const deployDiagram = async (filename: any) => {
        Swal.fire({
            title: 'Are you sure?',
            text: `Do you want to start the deployment for the BPMN model named "${filename}"?`,
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Yes, start deployment',
            cancelButtonText: 'No, cancel',
        }).then(async (result) => {
            if (result.isConfirmed) {
                console.log('To the deployment!');
                notyf.success({
                    type: 'warning',
                    message: 'Starting the Deployment of the BPMN model...'
                });
                // Here, you would add any logic needed to actually deploy the model
                // For example, an API call to a server that handles the deployment
            } else if (result.isDismissed) {
                console.log('Deployment cancelled.');
                notyf.error('Deployment cancelled!');
            }
        });
    };

    return (
        <div>
            <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'left' }}>
                {fileList.length === 0 && deviceArray.length === 0 ? (
                    <div style={{ margin: '20px', fontSize: '28px', color: '#555', textAlign: 'center', width: '100%' }}>
                        Create or Import a new .bpmn diagram to start
                    </div>
                ) : (
                    <>
                        <div>
                            {fileList.length > 0 && (
                                <>
                                    <h3 style={{ margin: '10px' }}>BPMN Models</h3>
                                    {fileList.map((file, index) => (
                                        <div className="file-info" key={index} style={{ display: 'flex' }}>
                                            <div
                                                style={{
                                                    border: '1px solid rgba(0, 0, 0, 0.05)',
                                                    margin: '10px',
                                                    padding: '1px',
                                                    borderRadius: '5px',
                                                    marginBottom: '0px',
                                                    fontSize: '15px',
                                                    color: 'black',
                                                    display: 'flex',
                                                    width: '100%',
                                                    alignItems: 'center',
                                                }}
                                            >
                                                <BsDiagram2 style={{ padding: '10px', fontSize: '22px' }} />
                                                <div
                                                    className="file-info-item-name file-name"
                                                    style={{
                                                        fontSize: '14px',
                                                        padding: '5px',
                                                        color: 'black',
                                                        marginRight: 'auto',
                                                        whiteSpace: 'nowrap',
                                                        overflow: 'hidden',
                                                        textOverflow: 'ellipsis',
                                                        maxWidth: '50%',
                                                    }}
                                                    title={file}
                                                >
                                                    {file.length > 20 ? `${file.substring(0, 20)}...` : file}
                                                </div>
    
                                                <button
                                                    style={{
                                                        margin: '5px',
                                                        background: '#aad4de',
                                                        color: '#324e6c',
                                                        fontSize: '15px',
                                                        padding: '8px 12px',
                                                        borderRadius: '5px',
                                                        border: '1px solid #324e6c',
                                                        cursor: 'pointer',
                                                        display: 'flex',
                                                        alignItems: 'center',
                                                    }}
                                                    onClick={() => loadDiagram(file)}
                                                >
                                                    <MdFileUpload style={{ marginRight: '5px' }} /> Load
                                                </button>
    
                                                <button
                                                    style={{
                                                        margin: '5px',
                                                        background: '#aad4de',
                                                        color: '#324e6c',
                                                        fontSize: '15px',
                                                        padding: '8px 12px',
                                                        borderRadius: '5px',
                                                        border: '1px solid #324e6c',
                                                        cursor: 'pointer',
                                                        display: 'flex',
                                                        alignItems: 'center',
                                                    }}
                                                    onClick={() => deleteDiagram(file)}
                                                >
                                                    <MdDeleteForever style={{ marginRight: '5px' }} /> Delete
                                                </button>
    
                                                <button
                                                    style={{
                                                        margin: '5px',
                                                        background: '#aad4de',
                                                        color: '#324e6c',
                                                        fontSize: '15px',
                                                        padding: '8px 12px',
                                                        borderRadius: '5px',
                                                        border: '1px solid #324e6c',
                                                        cursor: 'pointer',
                                                        display: 'flex',
                                                        alignItems: 'center',
                                                    }}
                                                    onClick={() => deployDiagram(file)}
                                                >
                                                    <MdPlayCircleOutline style={{ marginRight: '5px' }} /> Deploy
                                                </button>
    
                                                <button
                                                    style={{
                                                        margin: '5px',
                                                        background: '#aad4de',
                                                        color: '#324e6c',
                                                        fontSize: '15px',
                                                        padding: '8px 12px',
                                                        borderRadius: '5px',
                                                        border: '1px solid #324e6c',
                                                        cursor: 'pointer',
                                                        display: 'flex',
                                                        alignItems: 'center',
                                                    }}
                                                    onClick={() => simulateDiagram(file)}
                                                >
                                                    <MdAutoGraph style={{ marginRight: '5px' }} /> Simulate
                                                </button>
                                            </div>
                                        </div>
                                    ))}
                                </>
                            )}
                        </div>
                        <div>
                            {deviceArray.length > 0 && (
                                <>
                                    <h3 style={{ marginLeft: '15px', marginTop: '15px' }}>Available Devices</h3>
                                    <div
                                        className="library-container"
                                        style={{ display: 'flex', flexDirection: 'row', marginTop: '10px' }}
                                    >
                                        {deviceArray.map((item: any, index: number) => (
                                            <div key={index} className="platform-device">
                                                <div style={{ fontSize: '35px', fontWeight: 'bold' }}>{item.name}</div>
                                                <div>
                                                    {item.state === 'exists' ? (
                                                        <div>
                                                            <span style={{ fontWeight: 'bold' }}> State:</span>{' '}
                                                            <span style={{ fontWeight: 'normal' }}>{item.state}</span>
                                                            <span style={{ fontWeight: 'bold' }}> Availability:</span>{' '}
                                                            <span style={{ fontWeight: 'normal' }}>
                                                                Online <div className="online-dot"></div>
                                                            </span>
                                                        </div>
                                                    ) : (
                                                        <div>
                                                            {item.state === 'ended' && (
                                                                <div>
                                                                    <span style={{ fontWeight: 'bold' }}> State:</span>{' '}
                                                                    <span style={{ fontWeight: 'normal' }}>{item.state}</span>
                                                                    <span style={{ fontWeight: 'bold' }}> Availability:</span>{' '}
                                                                    <span style={{ fontWeight: 'normal' }}>
                                                                        Offline<div className="offline-dot"></div>
                                                                    </span>
                                                                </div>
                                                            )}
                                                        </div>
                                                    )}
                                                </div>
    
                                                <div
                                                    style={{
                                                        fontSize: '70%',
                                                        fontWeight: 'bold',
                                                        position: 'relative',
                                                        color: '#343232',
                                                        bottom: '-12px',
                                                    }}
                                                >
                                                    {item.id}
                                                </div>
                                            </div>
                                        ))}
                                    </div>
                                </>
                            )}
                        </div>
                    </>
                )}
            </div>
    
            <button
                style={{
                    margin: '15px',
                    fontWeight: 'bold',
                    background: '#aad4de',
                    color: '#324e6c',
                    fontSize: '15px',
                    padding: '10px 10px',
                    borderRadius: '5px',
                    border: '2px solid #324e6c',
                    cursor: 'pointer',
                    marginTop: '2%',
                    marginBottom: '0.12cm',
                }}
            >
                <label htmlFor="import-diagram" style={{ cursor: 'pointer' }}>
                    Import New Diagram
                </label>
                <input id="import-diagram" type="file" accept=".bpmn" style={{ display: 'none' }} onChange={importNewDiagram} />
            </button>
            <button
                style={{
                    margin: '15px',
                    fontWeight: 'bold',
                    background: '#aad4de',
                    color: '#324e6c',
                    fontSize: '15px',
                    padding: '10px 10px',
                    borderRadius: '5px',
                    border: '2px solid #324e6c',
                    cursor: 'pointer',
                    marginTop: '2%',
                    marginBottom: '0.42cm',
                }}
                onClick={createNewDiagram}
            >
                Create New Diagram
            </button>
            <button
                style={{
                    margin: '15px',
                    fontWeight: 'bold',
                    background: '#aad4de',
                    color: '#324e6c',
                    fontSize: '15px',
                    padding: '10px 10px',
                    borderRadius: '5px',
                    border: '2px solid #324e6c',
                    cursor: 'pointer',
                    marginTop: '2%',
                    marginBottom: '0.42cm',
                }}
                onClick={saveDiagram}
            >
                Save Current Diagram
            </button>
    
            <div id="bpmn-container" style={{ height: '600px', border: 'solid 0.1px', margin: '15px' }}></div>
        </div>
    );
}