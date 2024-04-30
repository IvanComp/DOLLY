import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './x.css';
import {toast} from "react-toastify";
import Swal from "sweetalert2";

interface Platform {
    id: any;
    name: any;
    state: any;
}

interface Device {
    id: any;
    name: any;
    state: any;
}
export default function MicroservicesView() {
    const [dataArray, setDataArray] = useState<Platform[]>([]);
    const [featureArray, setFeatureArray] = useState<any[]>([]);
    const [deviceArray, setDeviceArray] = useState<any[]>([]);
    const [selectedFeature, setSelectedFeature] = useState<any | null>(null);
    const [selectedDevice, setSelectedDevice] = useState<any | null>(null);

    useEffect(() => {
        fetchData();
    }, []); // Chiamata una sola volta al caricamento del componente


    const fetchData = async () => {
        try {

            const platformResponse = await getPlatform();
            setDataArray(platformResponse);

            const featureResponse = await getFeature();
            setFeatureArray(featureResponse);

        } catch (error) {
            console.error('Errore durante il recupero dei dati:', error);
        }
    };

    const createPlatform = async () => {
        try {

            const { value: platformName } = await Swal.fire({
                title: 'Enter Platform Name',
                input: 'text',
                inputPlaceholder: 'Platform Name',
                showCancelButton: true,
                confirmButtonText: 'Create Platform',
                cancelButtonText: 'Cancel',
                // @ts-ignore
                inputValidator: (value) => {
                    if (!value) {
                        return "Platform name is required."
                    }
                }
            });

            if (platformName) {
                const data = { name: platformName };
                const response = await axios.post('http://localhost:4567/platform/mecrplatform', data, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                console.log(JSON.stringify(response.data));
                await fetchData();
                console.log('Platform created successfully!');
            }
        } catch (error) {
            console.error('Error during platform creation:', error);
        }
    };
    const endPlatform = async (index: number) => {
        const platformId = dataArray[index].id;
        const confirmation = window.confirm(`Are you sure to end the Platform Instance with ID: ${platformId}?`);

        if (confirmation) {

            try {
                const url = `http://localhost:4567/platform/${platformId}/meendplatform`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));
                await fetchData();
            } catch (error) {
                console.error('Error during deleting the Platform:', error);
            } finally {
            }
        }
    };
    const deletePlatform = async (index: number) => {
        const platformId = dataArray[index].id;
        const confirmation = window.confirm(`Are you sure to delete the Platform with ID: ${platformId}?`);

        if (confirmation) {

            try {
                const url = `http://localhost:4567/platform/${platformId}`;

                // Chiamata DELETE all'API
                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));

                // Rimuovi l'elemento corrispondente da dataArray
                const updatedDataArray = dataArray.filter(item => item.id !== platformId);
                setDataArray(updatedDataArray);

            } catch (error) {
                console.error('Error during deleting the Platform:', error);
            } finally {
            }
        }
    };
    const getPlatform = async (): Promise<Platform[]> => {
        try {
            const response = await axios.get('http://localhost:4567/platform', {
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

    const createFeature = async () => {

        try {
            // Chiedi all'utente di inserire il nome della feature
            const featureName = window.prompt('Enter the name for the feature:', 'DefaultFeature');

            // Verifica se l'utente ha inserito un nome
            if (featureName !== null) {
                const data = {
                    name: featureName,
                };

                const response = await axios.post('http://localhost:4567/featureofinterest/mecrfeatureofinterest', data, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                console.log(JSON.stringify(response.data));
                await fetchData();
            } else {
                console.log('User canceled feature creation.');
            }
        } catch (error) {
            console.error('Errore durante la creazione della feature:', error);
        } finally {
        }
    };
    const endFeature = async (index: number) => {
        const featureId = featureArray[index].id;
        const confirmation = window.confirm(`Are you sure to end the Feature of Interest Instance with ID: ${featureId}?`);

        if (confirmation) {

            try {
                const url = `http://localhost:4567/featureofinterest/${featureId}/meendfeatureofinterest`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));
                await fetchData();
            } catch (error) {
                console.error('Error during deleting the Feature:', error);
            } finally {
            }
        }
    };
    const deleteFeature = async (index: number) => {
        const featureId = featureArray[index].id;
        const confirmation = window.confirm(`Are you sure to delete the Feature of Interest with ID: ${featureId}?`);

        if (confirmation) {

            try {
                const url = `http://localhost:4567/featureofinterest/${featureId}`;

                // Chiamata DELETE all'API
                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));

                // Rimuovi l'elemento corrispondente da featureArray
                const updatedFeatureArray = featureArray.filter(item => item.id !== featureId);
                setFeatureArray(updatedFeatureArray);

            } catch (error) {
                console.error('Error during deleting the Feature:', error);
            } finally {

            }
        }
    };
    const getFeature = async (): Promise<any[]> => {
        try {
            const response = await axios.get('http://localhost:4567/featureofinterest', {
                headers: {
                    'Content-Type': 'text/plain'
                },
            });

            console.log(JSON.stringify(response.data));
            return response.data;
        } catch (error) {
            console.error('Errore durante il recupero dei dati della Feature:', error);
            throw error;
        }
    };


    async function open3D() {
        try {
            const url = '3d';
            window.open(url, '_blank');
            toast.success('All files deleted successfully!', {position: "bottom-right", autoClose: 1000, hideProgressBar: true, closeOnClick: true, pauseOnHover: true, draggable: true, progress: undefined, theme: "colored",});
        } catch (error) {
            console.error(error);
        }
    }
    return (
        <div className="flex flex-col items-start justify-start p-l text-center box-border">
            <h1 style={{marginLeft: '5px', marginTop: '5px'}}>Available Platforms:</h1>

            {dataArray.length >= 0 && (
                <div className="library-container"
                     style={{display: 'flex', flexDirection: 'row', marginTop: '10px'}}>
                    {dataArray.map((item: Platform, index: number) => (
                        <div key={index} className="platform-container">
                            <div style={{fontSize: "35px", fontWeight: "bold"}}>{item.name}</div>
                            <div>
                                {item.state === 'exists' ? (
                                    <div>
                                        <span style={{fontWeight: "bold"}}> State:</span> <span
                                        style={{fontWeight: "normal"}}>{item.state}</span>
                                        <span style={{fontWeight: "bold"}}> Availability:</span> <span
                                        style={{fontWeight: "normal"}}> Online <div
                                        className="online-dot"></div></span>
                                    </div>
                                ) : (
                                    <div>
                                        {item.state === 'ended' && (
                                            <div>
                                                <span style={{fontWeight: "bold"}}> State:</span> <span
                                                style={{fontWeight: "normal"}}>{item.state}</span>
                                                <span style={{fontWeight: "bold"}}> Availability:</span> <span
                                                style={{fontWeight: "normal"}}> Offline<div
                                                className="offline-dot"></div></span>
                                            </div>
                                        )}
                                    </div>
                                )}
                            </div>

                            <div style={{display: 'flex', gap: '10px'}}>
                                <button style={{fontSize: "12px", backgroundColor: '#da1540', color: 'white', fontWeight: "bold", padding: '5px 13px', border: '2px solid red', borderRadius: '3px', cursor: 'pointer', position: 'relative', bottom: '-5px'}} onClick={() => endPlatform(index)}>End Platform</button>
                                <button style={{fontSize: "12px", backgroundColor: '#da1540', color: 'white', fontWeight: "bold", padding: '5px 13px', border: '2px solid red', borderRadius: '3px', cursor: 'pointer', position: 'relative', bottom: '-5px'}} onClick={() => deletePlatform(index)}>Delete Platform</button>
                            </div>

                            <div className="small-font">{item.id}</div>
                        </div>
                    ))}

                    <button className="platform-container" style={{background: 'white', color: '#10ad73', fontSize: '20px', padding: '10px 30px', borderRadius: '5px', border: '1px dashed green', cursor: 'pointer', marginTop: '2%', marginBottom: '0.42cm', justifyContent: 'center', alignItems: 'center', textAlign: 'center',}} onClick={createPlatform}> + <br></br>Create Platform</button>

                </div>
            )}

            <h1 style={{marginLeft: '5px', marginTop: '5px'}}>Available Features of Interest:</h1>
            {featureArray.length >= 0 && (
                <div className="library-container"
                     style={{display: 'flex', flexDirection: 'row', marginTop: '10px'}}>
                    {featureArray.map((item: any, index: number) => (
                        <div key={index} className="platform-feature">
                            <div style={{fontSize: "35px", fontWeight: "bold"}}>{item.name}</div>
                            <div>
                                {item.state === 'exists' ? (
                                    <div>
                                        <span style={{fontWeight: "bold"}}> State:</span> <span style={{fontWeight: "normal"}}>{item.state}</span>
                                        <span style={{fontWeight: "bold"}}> Availability:</span> <span style={{fontWeight: "normal"}}> Online <div className="online-dot"></div></span>
                                    </div>
                                ) : (
                                    <div>
                                        {item.state === 'ended' && (
                                            <div>
                                                <span style={{fontWeight: "bold"}}> State:</span> <span style={{fontWeight: "normal"}}>{item.state}</span>
                                                <span style={{fontWeight: "bold"}}> Availability:</span> <span style={{fontWeight: "normal"}}> Offline<div className="offline-dot"></div></span>
                                            </div>
                                        )}
                                    </div>
                                )}
                            </div>
                            <div style={{display: 'flex', gap: '10px'}}>
                                <button style={{fontSize: "12px", backgroundColor: '#da1540', color: 'white', fontWeight: "bold", padding: '5px 13px', border: '2px solid red', borderRadius: '3px', cursor: 'pointer', position: 'relative', bottom: '-5px'}} onClick={() => endFeature(index)}>End Feature of Interest</button>
                                <button style={{fontSize: "12px", backgroundColor: '#da1540', color: 'white', fontWeight: "bold", padding: '5px 13px', border: '2px solid red', borderRadius: '3px', cursor: 'pointer', position: 'relative', bottom: '-5px'}} onClick={() => deleteFeature(index)}>Delete Feature of Interest</button>
                            </div>

                            <div style={{fontSize: "70%", fontWeight: "bold", position: "relative", color: "#343232", bottom: "-12px"}}>{item.id}</div>
                        </div>
                    ))}

                    <button className="platform-feature" style={{background: 'white', color: '#9bbbd5', fontSize: '20px', padding: '10px 30px', borderRadius: '5px', border: '1px dashed #9BBBD5FF', cursor: 'pointer', marginTop: '2%', marginBottom: '0.42cm', justifyContent: 'center', alignItems: 'center', textAlign: 'center',}} onClick={createFeature}> + <br></br>Create <br></br>Feature Of Interest</button>

                </div>
            )}

        </div>
    );
}
