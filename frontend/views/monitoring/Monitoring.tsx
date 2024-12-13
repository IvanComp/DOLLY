import React, { useEffect, useState, useRef } from 'react';
import { BsDiagram2 } from "react-icons/bs";
import { Link } from "react-router-dom";
import axios from "axios";
import { MdAutoGraph } from "react-icons/md";
import '../microservice/fileList.css';
import { Notyf } from "notyf";

export default function Monitoring() {

    const [fileList, setFileList] = useState<string[]>([]);

    useEffect(() => {
        fetchDiagramList();
    }, []);

    const notyf = new Notyf({
        duration: 3000,
        ripple: false,
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

    const fetchDiagramList = async () => {
        try {
            const response = await axios.get('/list-diagrams');
            setFileList(response.data);
        } catch (error) {
            console.error('Failed to fetch diagrams:', error);
            notyf.error('Failed to load diagram list!');
        }
    };

    const deployDiagram = async (file: string) => {

    };

    return (
        <div style={{margin:"15px"}}>
            <h2 style={{marginLeft: '0px', marginTop:"20px"}}>BPMN Models</h2>
    
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
                            width: '60%',
                            alignItems: 'center',
                        }}
                    >
                        <BsDiagram2 style={{ padding: '10px', fontSize: '22px'}} />
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
                            {file}
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
                                fontWeight: 'bold',
                                alignItems: 'center',
                            }}
                            onClick={() => deployDiagram(file)}
                        >
                           <MdAutoGraph style={{ marginRight: '5px' }} /> Deploy Business Process 
                        </button>
                    </div>
                </div>
            ))}
        </div>
    );
}
