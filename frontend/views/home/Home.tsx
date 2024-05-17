import React from "react";
import {Link} from "react-router-dom";

export default function Home() {
    return (
        <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
            <h1>HOME PAGE UNDER DEVELOPMENT</h1>

            <Link to="/bpmn">
                <button style={{margin: '15px', fontWeight: "bold", background: '#aad4de', color: '#324e6c', fontSize: '15px', padding: '10px 10px', borderRadius: '5px', border: '2px solid #324e6c', cursor: 'pointer', marginTop: '2%', marginBottom: '0.42cm'}}>Get Started</button>
            </Link>

        </div>
    );
}

