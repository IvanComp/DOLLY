import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function MicroservicesView() {
    const [platformData, setPlatformData] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [showCreateButton, setShowCreateButton] = useState(true);

    const createPlatform = async () => {
        try {
            setIsLoading(true);

            // Chiamata al backend per creare la piattaforma
            const response = await axios.post('/platform/mecrplatform');
            const platformId = response.data.platformId;

            const platformResponse = await axios.get(
                'http://localhost:4567/platform/mecrplatform'
            );

            // Imposta i dati della piattaforma nello stato
            setPlatformData(platformResponse.data);
            setIsLoading(false);
            setShowCreateButton(false);
        } catch (error) {
            console.error('Si è verificato un errore durante la creazione della piattaforma:', error);
            setIsLoading(false);
        }
    };

    return (
        <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
            {showCreateButton && (
                <button onClick={createPlatform}>Crea Platform</button>
            )}

            {isLoading ? (
                <p>Creazione della piattaforma in corso...</p>
            ) : (
                <div>
                    {platformData && (
                        <div className="platform-container">
                            <h1>Platform Data:</h1>
                            <pre>{JSON.stringify(platformData, null, 2)}</pre>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
}
