import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AOS from "aos";
import "aos/dist/aos.css";
import "./home.css";

const Home: React.FC = () => {
    const [showSteps, setShowSteps] = useState(false);

    useEffect(() => {
        AOS.init({
            duration: 3000,
            once: true, // Ensures animations happen only once
        });

        const handleScroll = () => {
            const arrowPosition = document.querySelector('.arrow')?.getBoundingClientRect().top || 0;
            const windowHeight = window.innerHeight;

            if (arrowPosition < windowHeight) {
                setShowSteps(true);
            }
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);


    return (
        <div className="container">
            <section className="steps">
                <div className="step-row">
                    <div className="step" data-aos="fade-right">
                        <h2>Step 1: IoT Domain Modeling</h2>
                        <p>Define a set of Digital Shadows for mirroring IoT entities.</p>
                        <Link to="/iot">
                            <button className="get-started-button">Get Started</button>
                        </Link>
                    </div>
                    <div className="step" data-aos="fade-left">
                        <h2>Step 2: Process Modeling</h2>
                        <p>Model, save and edit Business Processes using the Standard BPMN 2.0.</p>
                        <Link to="/bpmn">
                            <button className="get-started-button">Get Started</button>
                        </Link>
                    </div>
                </div>
                <div className="arrow bounce">
                    <p>⬇</p>
                </div>
                <div className={`step-row ${showSteps ? 'show' : 'hidden'}`}>
                    <div className="step" data-aos="fade-right">
                        <h2>Step 3: Process Simulation</h2>
                        <p>Learn how to use the main features of the app effectively.</p>
                        <Link to="/simulation">
                            <button className="get-started-button">Get Started</button>
                        </Link>
                    </div>
                    <div className="step" data-aos="fade-left">
                        <h2>Step 4: Process Monitoring</h2>
                        <p>Track your progress and analyze data with our built-in tools.</p>
                        <Link to="/monitoring">
                            <button className="get-started-button">Get Started</button>
                        </Link>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Home;


