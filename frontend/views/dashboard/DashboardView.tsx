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
    const [deviceArray, setDeviceArray] = useState<Device[]>([]);
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

            const deviceResponse = await getDevice();
            setDeviceArray(deviceResponse);

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
                        return "Platform name is required.";
                    }
                }
            });

            if (platformName) {
                const { value: formValues } = await Swal.fire({
                    title: 'Enter Additional Details',
                    html:
                        '<input id="swal-input1" class="swal2-input" placeholder="Description">' +
                        '<input id="swal-input2" class="swal2-input" placeholder="hostedBy">',
                    focusConfirm: false,
                    showCancelButton: true,
                    preConfirm: () => {
                        const input1 = document.getElementById('swal-input1') as HTMLInputElement;
                        const input2 = document.getElementById('swal-input2') as HTMLInputElement;
                        return [
                            input1 ? input1.value : '', // Ensure input1 is treated as HTMLInputElement
                            input2 ? input2.value : '' // Ensure input2 is treated as HTMLInputElement
                        ];
                    }
                });

                if (formValues) {
                    const [description, hostedby] = formValues;
                    const data = { name: platformName, description, hostedby };
                    const response = await axios.post('http://localhost:4567/platform/mecrplatform', data, {
                        headers: {
                            'Content-Type': 'application/json',
                        },
                    });

                    console.log(JSON.stringify(response.data));
                    await fetchData(); // Make sure fetchData() is defined or handled appropriately
                    console.log('Platform created successfully!');
                }
            }
        } catch (error) {
            console.error('Error during platform creation:', error);
        }
    };
    const endPlatform = async (index: number) => {
        const platformId = dataArray[index].id;
        const { value: confirmation } = await Swal.fire({title: 'Confirm Termination', text: `Are you sure you want to end the Platform Instance with ID: ${platformId}?`, icon: 'warning', showCancelButton: true, confirmButtonColor: '#3085d6', cancelButtonColor: '#d33', confirmButtonText: 'Yes, end it!', cancelButtonText: 'Cancel'});
        if (confirmation) {
            try {
                const url = `http://localhost:4567/platform/${platformId}/meendplatform`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));
                await fetchData(); // Make sure fetchData() is defined or handled appropriately
            } catch (error) {
                console.error('Error during deleting the Platform:', error);
            }
        }
    };
    const deletePlatform = async (index: number) => {
        const platformId = dataArray[index].id;

        const { value: confirmation } = await Swal.fire({title: 'Confirm Deletion', text: `Are you sure to delete the Platform with ID: ${platformId}?`, icon: 'warning', showCancelButton: true, confirmButtonColor: '#d33', // Red color to signify a delete actioncancelButtonColor: '#aaa',confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'Cancel'
        });

        if (confirmation) {
            try {
                const url = `http://localhost:4567/platform/${platformId}`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));

                // Remove the corresponding element from dataArray
                const updatedDataArray = dataArray.filter(item => item.id !== platformId);
                setDataArray(updatedDataArray);  // Ensure setDataArray is defined or handled appropriately

            } catch (error) {
                console.error('Error during deleting the Platform:', error);
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


    const createDevice = async () => {
        try {

            const { value: deviceName } = await Swal.fire({
                title: 'Enter Device Name',
                input: 'text',
                inputPlaceholder: 'Device Name',
                showCancelButton: true,
                confirmButtonText: 'Create Device',
                cancelButtonText: 'Cancel',
                // @ts-ignore
                inputValidator: (value) => {
                    if (!value) {
                        return "Device name is required.";
                    }
                }
            });

            if (deviceName) {
                const { value: formValues } = await Swal.fire({
                    title: 'Enter Additional Details',
                    html:
                        '<input id="swal-input1" class="swal2-input" placeholder="Description">' +
                        '<label for="swal-input2" style="display:block; margin-top:20px; margin-bottom:10px;">Device Status</label>' +
                        '<select id="swal-input2" class="swal2-input">' +
                        '<option value="true">ON</option>' +
                        '<option value="false">OFF</option>' +
                        '</select>',
                    focusConfirm: false,
                    showCancelButton: true,
                    preConfirm: () => {
                        const input1 = document.getElementById('swal-input1') as HTMLInputElement;
                        const input2 = document.getElementById('swal-input2') as HTMLSelectElement;
                        return [
                            input1 ? input1.value : '',
                            input2 ? input2.value : '' // Convert the string to boolean
                        ];
                    }
                });

                if (formValues) {
                    const [description, status] = formValues;
                    const data = { name: deviceName, description, status }; // status is already a boolean here
                    const response = await axios.post('http://localhost:4567/device/mecrdevice', data, {
                        headers: {
                            'Content-Type': 'application/json',
                        },
                    });

                    console.log(JSON.stringify(response.data));
                    await fetchData();
                    console.log('Device created successfully!');
                }
            }
        } catch (error) {
            console.error('Error during device creation:', error);
        }
    }
    const endDevice = async (index: number) => {
        const deviceId = deviceArray[index].id;

        const { value: confirmation } = await Swal.fire({
            title: 'Confirm Termination',
            text: `Are you sure to end the Device Instance with ID: ${deviceId}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, end it!',
            cancelButtonText: 'Cancel'
        });

        if (confirmation) {
            try {
                const url = `http://localhost:4567/device/${deviceId}/meenddevice`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));
                await fetchData();  // Ensure fetchData() is defined or handled appropriately
            } catch (error) {
                console.error('Error during ending the Device:', error);
            }
        }
    };
    const deleteDevice = async (index: number) => {
        const deviceId = deviceArray[index].id;
        const confirmation = window.confirm(`Are you sure to delete the Device with ID: ${deviceId}?`);

        if (confirmation) {

            try {
                const url = `http://localhost:4567/device/${deviceId}`;

                // Chiamata DELETE all'API
                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));

                // Rimuovi l'elemento corrispondente da dataArray
                const updatedDataArray = dataArray.filter(item => item.id !== deviceId);
                setDataArray(updatedDataArray);

            } catch (error) {
                console.error('Error during deleting the Device:', error);
            } finally {
            }
        }
    }
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

    const createFeature = async () => {
        try {
            // SweetAlert2 dialog to ask the user to enter the name of the feature

            const { value: featureName } = await Swal.fire({
                title: 'Enter the name for the feature',
                input: 'text',
                inputPlaceholder: 'DefaultFeature',
                // @ts-ignore
                inputValidator: (value) => {
                    if (!value) {
                        return 'You need to write something!';
                    }
                },
                showCancelButton: true,
                confirmButtonText: 'Create Feature',
                cancelButtonText: 'Cancel'
            });

            // Check if the user has entered a name
            if (featureName) {
                const data = {
                    name: featureName,
                };

                const response = await axios.post('http://localhost:4567/featureofinterest/mecrfeatureofinterest', data, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                console.log(JSON.stringify(response.data));
                await fetchData();  // Make sure fetchData() is defined or handled appropriately
            } else {
                console.log('User canceled feature creation.');
            }
        } catch (error) {
            console.error('Error during the creation of the feature:', error);
        }
    };
    const endFeature = async (index: number) => {
        const featureId = featureArray[index].id;

        const { value: confirmation } = await Swal.fire({
            title: 'Confirm Termination',
            text: `Are you sure you want to end the Feature of Interest Instance with ID: ${featureId}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, end it!',
            cancelButtonText: 'Cancel'
        });

        if (confirmation) {
            try {
                const url = `http://localhost:4567/featureofinterest/${featureId}/meendfeatureofinterest`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));
                await fetchData(); // Ensure fetchData() is properly defined or handled in your application
            } catch (error) {
                console.error('Error during ending the Feature:', error);
            }
        }
    };
    const deleteFeature = async (index: number) => {
        const featureId = featureArray[index].id;

        const { value: confirmation } = await Swal.fire({
            title: 'Confirm Deletion',
            text: `Are you sure to delete the Feature of Interest with ID: ${featureId}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',  // Red color to emphasize deletion
            cancelButtonColor: '#aaa',
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'Cancel'
        });

        if (confirmation) {
            try {
                const url = `http://localhost:4567/featureofinterest/${featureId}`;

                const response = await axios.delete(url, {
                    headers: {
                        'Content-Type': 'text/plain',
                    }
                });

                console.log(JSON.stringify(response.data));

                // Remove the corresponding element from featureArray
                const updatedFeatureArray = featureArray.filter(item => item.id !== featureId);
                setFeatureArray(updatedFeatureArray); // Ensure setFeatureArray is defined or handled appropriately

            } catch (error) {
                console.error('Error during deleting the Feature:', error);
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
        try {const url = '3d';window.open(url, '_blank');toast.success('All files deleted successfully!', {position: "bottom-right", autoClose: 1000, hideProgressBar: true, closeOnClick: true, pauseOnHover: true, draggable: true, progress: undefined, theme: "colored",});
        } catch (error) {console.error(error);}
    }



    return (
        <div className="flex flex-col items-start justify-start p-l text-center box-border">
            <h3 style={{marginLeft: '5px'}}>Available Platforms:</h3>

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

            <h3 style={{marginLeft: '5px', marginTop: '5px'}}>Available Features of Interest:</h3>
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
            <h3 style={{marginLeft: '5px', marginTop: '5px'}}>Available Devices:</h3>
            {deviceArray.length >= 0 && (
                <div className="library-container"
                     style={{display: 'flex', flexDirection: 'row', marginTop: '10px'}}>
                    {deviceArray.map((item: Device, index: number) => (
                        <div key={index} className="platform-device">
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
                                <button
                                    style={{
                                        fontSize: "12px",
                                        backgroundColor: '#da1540',
                                        color: 'white',
                                        fontWeight: "bold",
                                        padding: '5px 13px',
                                        border: '2px solid red',
                                        borderRadius: '3px',
                                        cursor: 'pointer',
                                        position: 'relative',
                                        bottom: '-5px'
                                    }}
                                    onClick={() => endDevice(index)}
                                >
                                    End Device
                                </button>
                                <button
                                    style={{
                                        fontSize: "12px",
                                        backgroundColor: '#da1540',
                                        color: 'white',
                                        fontWeight: "bold",
                                        padding: '5px 13px',
                                        border: '2px solid red',
                                        borderRadius: '3px',
                                        cursor: 'pointer',
                                        position: 'relative',
                                        bottom: '-5px'
                                    }}
                                    onClick={() => deleteDevice(index)}
                                >
                                    Delete Device
                                </button>
                            </div>

                            <div style={{
                                fontSize: "70%",
                                fontWeight: "bold",
                                position: "relative",
                                color: "#343232",
                                bottom: "-12px"
                            }}>{item.id}</div>
                        </div>
                    ))}

                    <button className="platform-feature" style={{
                        background: 'white',
                        color: '#d5cb9b',
                        fontSize: '20px',
                        padding: '10px 30px',
                        borderRadius: '5px',
                        border: '1px dashed #D5CB9BFF',
                        cursor: 'pointer',
                        marginTop: '2%',
                        marginBottom: '0.42cm',
                        justifyContent: 'center',
                        alignItems: 'center',
                        textAlign: 'center',
                    }} onClick={createDevice}> + <br></br>Create <br></br>Device
                    </button>
                    <input style={{
                        position: 'absolute',
                        top: "8px",
                        right: "42px",
                        zIndex: '9',
                        fontSize: "17px",
                        backgroundColor: '#F3F5F7',
                        color: '#334F6D',
                        fontWeight: "bold",
                        padding: '5px 13px',
                        border: '2px solid #334F6D',
                        borderRadius: '3px',
                        cursor: 'pointer'
                    }} onClick={open3D} disabled={true} type="submit" value="🔎 Open 3D View"/>
                </div>
            )}

        </div>
    );
}
