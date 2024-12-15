import React, { JSXElementConstructor, Key, ReactElement, ReactFragment, ReactPortal, useEffect, useState } from 'react';
// @ts-ignore
import BpmnModeler from 'bpmn-js/dist/bpmn-modeler.production.min.js';
import 'bpmn-js/dist/assets/diagram-js.css';
import './fileList.css';
import axios from 'axios';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import { MdOutlineInsertChartOutlined, MdChecklist, MdCancel, MdCheckCircle, MdMonitorHeart, MdAutoGraph, MdPlayCircleOutline, MdExpandMore, MdExpandLess } from "react-icons/md";
import { BsDiagram2 } from "react-icons/bs";
import { SiUml } from "react-icons/si";
import { MdOutlineSearch } from "react-icons/md";
import { MdFileUpload } from "react-icons/md";
import { MdOutlineBrokenImage } from "react-icons/md";
import { MdDeleteForever } from "react-icons/md";
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
    const [isValid, setisValid] = useState(1);  

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
            if (JSON.stringify(response.data) !== JSON.stringify(fileList)) {
                setFileList(response.data); 
            }
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
        event.preventDefault(); // Previene il comportamento predefinito
        event.stopPropagation();
        const file = event.target.files?.[0];
        if (!file) {
            notyf.error('No file selected!');
            return;
        }
    
        if (!file.name.endsWith('.bpmn')) {
            notyf.error('Invalid file format! Only .bpmn files are allowed.');
            return;
        }
    
        try {
            const fileContent = await file.text();
            const fileName = file.name;
    
            await axios.post('/save-diagram', {
                xml: fileContent,
                filename: fileName,
            }, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
    
            setFileList((prev) => [...prev, fileName]);
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
        try {
            await Swal.fire({
                title: 'Are you sure?',
                text: "Do you want to delete this BPMN model?",
                icon: 'warning',
                showDenyButton: true,
                confirmButtonText: 'Yes, delete it!',
                denyButtonText: 'No, cancel!',
            }).then(async (result) => {
                if (result.isConfirmed) {
                    await axios.delete(`/delete-diagram/${filename}`);
                    console.log('Diagram deleted successfully.');
                    notyf.success('Diagram deleted successfully!');
                    fetchDiagramList(); // Aggiorna solo la lista senza ricaricare la pagina
                } else {
                    console.log('Deletion cancelled.');
                    notyf.error('Deletion cancelled!');
                }
            });
        } catch (error) {
            console.error('Failed to delete diagram:', error);
            notyf.error('Failed to delete diagram!');
        }
    };
    const loadDiagram = async (filename: any) => {
        Swal.fire({
            title: 'Are you sure?',
            text: `Do you want to load the BPMN model: "${filename}"?`,
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
    const manageDiagram = async (filename: any) => {
        Swal.fire({
            title: 'Are you sure?',
            text: `Do you want to analyze the BPMN model: "${filename}"?`,
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No, cancel',
        }).then(async (result) => {
            if (result.isConfirmed) {
                console.log('To the deployment!');
                notyf.success({
                    type: 'warning',
                    message: 'Starting the Deployment of the BPMN model...'
                });
    
                // Esegui il redirect alla pagina /monitoring
                window.location.href = '/management';
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
                    <div style={{ margin: '20px', fontSize: '20px', color: '#777', fontStyle:"italic", textAlign: 'center', width: '100%' }}>
                        Create or Import a new BPMN diagram to start.
                    </div>
                ) : (
                    <>
                       <div>
  {fileList.length > 0 && (
    <>
      <h3 style={{ margin: "10px" }}>Process Models</h3>
      
      {/* Header Bar */}
      <div
        className="header-bar"
        style={{
          display: "grid",
          gridTemplateColumns: "minmax(100px, 200px) minmax(150px, 300px) minmax(150px, 200px) minmax(150px, 200px)",
          alignItems: "center",
          padding: "5px",
          marginLeft:"10px",
          borderBottom: "1px solid #ccc",
          background: "#f8f9fa",
          fontWeight: "bold",
          fontSize: "14px",
          textAlign: "center",
          width:"80%",
        }}
      >
        <div>File Name</div>
        <div>Data Model</div>
        <div>XML Schema Validation</div>
      </div>

      {/* File List */}
      {fileList.map((file, index) => (
        <div
          className="file-info-item-name file-name"
          key={index}
          style={{
            display: "grid",
            gridTemplateColumns: "2fr 2fr 2fr 2fr",
            alignItems: "center",
            marginLeft:"10px",
            padding: "1px",
            width:"80%",
            borderBottom: "1px solid #eee",
          }}
        >
          {/* File Name */}
          <div
            style={{
              display: "flex",
              alignItems: "center",
              fontSize: "14px",
              padding: "5px",
           
            }}
            title={file}
          >
            <BsDiagram2 style={{ padding: "5px", fontSize: "22px" }} />
            {file.length > 20 ? `${file.substring(0, 20)}...` : file}
          </div>

          {/* Data Model */}
          <div style={{ textAlign: "center" }}>
            <span style={{ padding: "5px 10px", background: "#f0f0f0", borderRadius: "5px" }}>
            <SiUml style={{ marginRight: "5px" }} /> {null}
            </span>
          </div>

          {/* Validation */}
          <div style={{ textAlign: "center" }}>
            {isValid === 1 ? (
              <span
                className="badge deployed"
                style={{
                  display: "inline-flex",
                  alignItems: "center",
                  background: "#d4edda",
                  color: "#155724",
                  padding: "5px 10px",
                  borderRadius: "5px",
                  fontSize: "14px",
                }}
              >
                <MdCheckCircle style={{ marginRight: "5px" }} />
                Valid
              </span>
            ) : (
              <span
                className="badge not-deployed"
                style={{
                  display: "inline-flex",
                  alignItems: "center",
                  background: "#f8d7da",
                  color: "#721c24",
                  padding: "5px 10px",
                  borderRadius: "5px",
                  fontSize: "14px",
                }}
              >
                <MdCancel style={{ marginRight: "5px" }} />
                Invalid
              </span>
            )}
          </div>

          {/* Actions */}
          <div style={{ display: "flex", justifyContent: "center", gap: "10px" }}>
            <button
              style={{
                background: "#aad4de",
                color: "#324e6c",
                fontSize: "13px",
                padding: "4px 6px",
                borderRadius: "5px",
                border: "1px solid #324e6c",
                cursor: "pointer",
                display: "flex",
                alignItems: "center",
              }}
              onClick={() => loadDiagram(file)}
            >
              <MdOutlineSearch style={{ marginRight: "5px" }} /> Visualize
            </button>
            <button
              style={{
                background: "#aad4de",
                color: "#324e6c",
                fontSize: "13px",
                padding: "4px 6px",
                borderRadius: "5px",
                border: "1px solid #324e6c",
                cursor: "pointer",
                display: "flex",
                alignItems: "center",
              }}
              onClick={() => deleteDiagram(file)}
            >
              <MdDeleteForever style={{ marginRight: "5px" }} /> Delete
            </button>
            <button
              style={{
                background: "#aad4de",
                color: "#324e6c",
                fontSize: "13px",
                padding: "4px 6px",
                borderRadius: "5px",
                border: "1px solid #324e6c",
                cursor: "pointer",
                display: "flex",
                alignItems: "center",
              }}
              onClick={() => manageDiagram(file)}
            >
              <MdOutlineBrokenImage style={{ marginRight: "5px" }} /> Manage
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
                                    <h3 style={{ marginLeft: '15px', marginTop: '15px' }}>Data Models</h3>
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
                    margin: '10px',
                    fontWeight: 'bold',
                    background: '#aad4de',
                    color: '#324e6c',
                    fontSize: '12px',
                    padding: '10px 10px',
                    borderRadius: '5px',
                    border: '2px solid #324e6c',
                    cursor: 'pointer',
                    marginTop: "10px"

                }}
            >
                <label htmlFor="import-diagram" style={{ cursor: 'pointer'}}>
                    Import New Diagram
                </label>
                <input id="import-diagram" type="file" accept=".bpmn" style={{ display: 'none' }} onChange={importNewDiagram} />


            </button>
            <button
                style={{
                    margin: '10px',
                    fontWeight: 'bold',
                    background: '#aad4de',
                    color: '#324e6c',
                    fontSize: '12px',
                    padding: '10px 10px',
                    borderRadius: '5px',
                    border: '2px solid #324e6c',
                    cursor: 'pointer',
                }}
                onClick={createNewDiagram}
            >
                Create New Diagram
            </button>
            <button
                style={{
                    margin: '10px',
                    fontWeight: 'bold',
                    background: '#aad4de',
                    color: '#324e6c',
                    fontSize: '12px',
                    padding: '10px 10px',
                    borderRadius: '5px',
                    border: '2px solid #324e6c',
                    cursor: 'pointer',
                    marginTop: "30px"
                }}
                onClick={saveDiagram}
            >
                Save Current Diagram
            </button>
    
            <div id="bpmn-container" style={{ height: '600px', border: 'solid 0.1px', margin: '1px 10px' }}></div>
        </div>
    );
}