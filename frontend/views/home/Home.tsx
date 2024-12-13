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
                        <h2>Step 1: Data Modeling</h2>
                        <p>
                            Define the Data Model following the{" "}
                            <a
                                href="https://merode.econ.kuleuven.be/MERODE.html"
                                target="_blank"
                            >
                                MERODE Approach
                            </a>
                            .
                        </p>
                        <Link to="/domaindata">
                            <button className="get-started-button">
                                Get Started
                            </button>
                        </Link>
                    </div>
                    <div className="step" data-aos="fade-left">
                        <h2>Step 2: Process Modeling</h2>
                        <p>
                            Model, save and edit the Business Process using the
                            standard BPMN 2.0.
                        </p>
                        <Link to="/bpmn">
                            <button className="get-started-button">
                                Get Started
                            </button>
                        </Link>
                    </div>
                </div>

                <div
                    className="step-row"
                    style={{
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        width: "100%",
                        margin: "0 auto", // Center-align the row
                    }}
                    data-aos="fade-up"
                >
                    <div
                        className="step"
                        style={{
                            width: "100%", // Make it as wide as the two above
                            maxWidth: "1200px", // Optional: constrain max width
                            textAlign: "center", // Center-align the text
                        }}
                    >
                        <h2>Step 3: Process Simulation and Monitoring</h2>
                        <p>
                            Track your progress and analyze data with our
                            built-in tools.
                        </p>
                        <img
                            style={{
                                margin: "20px auto",
                                display: "block",
                                width: "100%",
                                maxWidth: "400px", // Reduced image size
                                height: "auto",
                            }}
                            src={monitoring}
                            alt="Monitoring Simulation"
                        />
                    </div>
                </div>
            </section>
        </div>
    );
};

export default Home;