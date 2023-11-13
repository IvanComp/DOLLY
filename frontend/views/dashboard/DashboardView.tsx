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
        let data = '{\r\n    "name": "Test",\r\n }';

        try {
            const response = await axios.post('http://localhost:4567/platform/mecrplatform', data, {
                headers: {
                    'Content-Type': 'text/plain',
                    'Access-Control-Allow-Origin': 'http://localhost:4567'
                }
            });

            console.log(JSON.stringify(response.data));
            fetchData(); // Aggiorna i dati dopo la creazione della piattaforma
        } catch (error) {
            console.error('Errore durante la creazione della piattaforma:', error);
        } finally {
            setIsLoading(false);
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

            {dataArray.length > 0 && (
                <div className="library-container" style={{ display: 'flex', flexDirection: 'row', marginTop: '10px' }}>
                    {dataArray.map((item: Platform, index: number) => (
                        <div key={index} className="platform-container">
                            <div>ID: {item.id}</div>
                            <div>Name: {item.name}</div>
                            <div>State: {item.state}</div>
                        </div>
                    ))}
                </div>
            )}

            {showCreateButton && (
                <button onClick={createPlatform}>Create Platform</button>
            )}

            {selectedPlatform && (
                <div>
                    <p>Dettagli della piattaforma selezionata: {selectedPlatform.name}</p>
                </div>
            )}
        </div>
    );
}
