import React, { useEffect, useState, useRef } from 'react';
import { BsDiagram2 } from "react-icons/bs";
import { Link } from "react-router-dom";
import axios from "axios";
import { Notyf } from "notyf";
// @ts-ignore
import { IframeSample } from "../../components/bimp-ui/IframeSample";

export default function Simulation() {

    const [fileList, setFileList] = useState([]);
    const iframeSampleRef = useRef<IframeSample>(null);

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

    const simulateDiagram = async (file: string) => {
        try {
            const response = await axios.get(`/get-diagram/${file}`, { responseType: 'text' });
            const fileContent = response.data;
            const encodedContent = btoa(fileContent);

            console.log(response.data);

            if (iframeSampleRef.current) {
                iframeSampleRef.current.loadBimpWithFile({
                    fileName: file,
                    fileContent: encodedContent,
                });
            }
        } catch (error) {
            console.error('Failed to simulate diagram:', error);
            notyf.error('Failed to simulate diagram!');
        }
    };

    return (
        <div>
            <h1 style={{ margin: '15px' }}>List of BPMN Models</h1>

            {fileList.map((file, index) => (
                <div className="file-info" key={index}>
                    <div style={{ border: "2px solid rgba(0, 0, 0, 0.05)", margin: "15px", padding: "1px", borderRadius: "5px", marginBottom: "1px", fontSize: "15px", color: "black", display: "flex", width: "auto", alignItems: "center" }}>
                        <BsDiagram2 style={{ marginRight: "2px" }} />
                        <p className="file-info-item-name file-name" style={{ fontSize: "15px", color: "black", display: "flex", alignItems: "center" }}>{file}</p>
                        <p className="file-info-item file-name">
                            <button style={{ margin: '5px', marginLeft: '20%', fontWeight: "bold", background: '#aad4de', color: '#324e6c', fontSize: '13px', padding: '5px 10px', borderRadius: '5px', border: '2px solid #324e6c', cursor: 'pointer' }} onClick={() => simulateDiagram(file)}>Simulate</button>
                        </p>
                    </div>
                </div>
            ))}

            <h1 style={{ margin: '15px' }}>BIMP BPMN Model Simulator</h1>

            <div style={{ margin: '15px' }}>
                <IframeSample ref={iframeSampleRef} />
            </div>
        </div>
    );
}
