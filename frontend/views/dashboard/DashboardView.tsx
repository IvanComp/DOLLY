import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './x.css';

interface Platform {
    id: any;
    name: any;
    state: any;
}

export default function MicroservicesView() {
    const [dataArray, setDataArray] = useState<Platform[]>([]);
    const [isLoading, setIsLoading] = useState(false);
    const [showCreateButton, setShowCreateButton] = useState(true);
    const [selectedPlatform, setSelectedPlatform] = useState<Platform | null>(null);

    useEffect(() => {
        fetchData();
    }, []); // Chiamata una sola volta al caricamento del componente

    const fetchData = async () => {
        try {
            setIsLoading(true);

            const response = await getPlatform();
            setDataArray(response);

            setIsLoading(false);
        } catch (error) {
            console.error('Errore durante il recupero dei dati:', error);
            setIsLoading(false);
        }
    };

    const createPlatform = async () => {
        setIsLoading(true);

        try {
            // Chiedi all'utente di inserire il nome della piattaforma
            const platformName = window.prompt('Enter the name for the platform:', 'DefaultName');

            // Verifica se l'utente ha inserito un nome
            if (platformName !== null) {
                const data = {
                    name: platformName,
                };

                const response = await axios.post('http://localhost:4567/platform/mecrplatform', data, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                console.log(JSON.stringify(response.data));
                await fetchData();
            } else {
                console.log('User canceled platform creation.');
            }
        } catch (error) {
            console.error('Errore durante la creazione della piattaforma:', error);
        } finally {
            setIsLoading(false);
        }
    };

    const endPlatform = async (index: number) => {
        const platformId = dataArray[index].id;
        const confirmation = window.confirm(`Are you sure to delete the Platform with ID: ${platformId}?`);

        if (confirmation) {
            setIsLoading(true);

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
                setIsLoading(false);
            }
        }
    };

    const deletePlatform = async (index: number) => {
        const platformId = dataArray[index].id;
        const confirmation = window.confirm(`Are you sure to delete the Platform with ID: ${platformId}?`);

        if (confirmation) {
            setIsLoading(true);

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
                setIsLoading(false);
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

    return (
        <div className="flex flex-col items-start justify-start p-l text-center box-border">
            <h1 style={{ marginLeft: '5px', marginTop: '5px' }}>Available Platforms:</h1>
            <button style={{background:'#10ad73', color: 'white', fontSize: '20px', padding: '10px 30px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '2%', marginBottom:'0.42cm'}} onClick={createPlatform}>Create Platform</button>

            {dataArray.length > 0 && (
                <div className="library-container" style={{ display: 'flex', flexDirection: 'row', marginTop: '10px' }}>
                    {dataArray.map((item: Platform, index: number) => (
                        <div key={index} className="platform-container">
                            <div style={{fontSize: "35px", fontWeight: "bold"}}>{item.name}</div>
                            <div>
                                {item.state === 'exists' ? (
                                    <div>
                                        <span style={{fontWeight: "bold"}}> State:</span> <span style={{fontWeight:"normal"}}>{item.state}</span>
                                        <span style={{fontWeight: "bold"}}> Availability:</span> <span style={{fontWeight:"normal"}}> Online <div className="online-dot"></div></span>
                                    </div>
                                ) : (
                                    <div>
                                        {item.state === 'ended' && (
                                            <div>
                                                <span style={{fontWeight: "bold"}}> State:</span> <span style={{fontWeight:"normal"}}>{item.state}</span>
                                                <span style={{fontWeight: "bold"}}> Availability:</span> <span style={{fontWeight:"normal"}}> Offline<div className="offline-dot"></div></span>
                                            </div>
                                        )}
                                    </div>
                                )}
                            </div>
                            <div style={{ display: 'flex', gap: '10px' }}>
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
                                    onClick={() => endPlatform(index)}
                                >
                                    End Platform
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
                                    onClick={() => deletePlatform(index)}
                                >
                                    Delete Platform
                                </button>
                            </div>

                            <div className="small-font">{item.id}</div>
                        </div>
                    ))}

                </div>
            )}

            {selectedPlatform && (
                <div>
                    <p>Dettagli della piattaforma selezionata: {selectedPlatform.name}</p>
                </div>
            )}
        </div>
    );
}
