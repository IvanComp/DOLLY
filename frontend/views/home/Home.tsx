import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AOS from "aos";
import "aos/dist/aos.css";
import "./home.css";
import developer from "Frontend/img/develop.gif";
import monitoring from "Frontend/img/monitoring.gif";

const Home: React.FC = () => {
    const [showSteps, setShowSteps] = useState(false);

    useEffect(() => {
        console.log("Initializing AOS");
        AOS.init({
            duration: 1500,
            once: true, // Ensures animations happen only once
        });

        const timer = setTimeout(() => {
            console.log("Showing steps after 2 seconds");
            setShowSteps(true);
        }, 2000);

        return () => clearTimeout(timer);
    }, []);

    return (
        <div className="container">
            <section className="steps">
                <div className="step-row" data-aos="fade-up">
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
                <div className="step-row" data-aos="fade-up">
                    <div className="step" data-aos="fade-right">
                        <h2>Step 3: Process Simulation</h2>
                        <p>Learn how to use the main features of the app effectively.</p>

                    </div>
                    <div className="step" data-aos="fade-left">
                        <h2>Step 4: Process Monitoring</h2>
                        <p>Track your progress and analyze data with our built-in tools.</p>
                        <img style={{margin: "10px", position:"relative"}}  src={monitoring} width="80%" height="180"/>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Home;