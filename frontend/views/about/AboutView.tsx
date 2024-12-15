import React, { useEffect } from 'react';
import './github.css';
import img0 from '../../img/bounce/0.svg';
import img1 from '../../img/bounce/1.svg';
import img2 from '../../img/bounce/2.svg';
import img3 from '../../img/bounce/3.svg';
import img4 from '../../img/bounce/4.svg';
import img5 from '../../img/bounce/5.svg';
import img6 from '../../img/bounce/6.svg';
import img7 from '../../img/bounce/7.svg';
import img8 from '../../img/bounce/8.svg';
import img9 from '../../img/bounce/9.svg';
import img10 from '../../img/bounce/10.svg';
import img11 from '../../img/bounce/11.svg';
import img12 from '../../img/bounce/12.svg';
import './github.css';
import developer from "../../img/develop.png"
import {BsGithub} from "react-icons/bs";

const DVDPlayer: React.FC = () => {
    useEffect(() => {
        const dvdElements = Array.from(
            document.getElementsByClassName('dvdLogo')
        ) as HTMLElement[];

        const screenWidth = window.innerWidth;
        const screenHeight = window.innerHeight;

        const dvdsData = dvdElements.map((dvdElement) => {
            const positionX = Math.random() * screenWidth;
            const positionY = Math.random() * screenHeight;
            const speedX = (Math.random() - 0.5) * 4; // Velocità casuale
            const speedY = (Math.random() - 0.5) * 4;

            return {
                element: dvdElement,
                positionX,
                positionY,
                speedX,
                speedY,
            };
        });

        const animateDVDs = () => {
            dvdsData.forEach((dvdData) => {
                let { positionX, positionY, speedX, speedY } = dvdData;

                // Calcolo della nuova posizione
                positionX += speedX;
                positionY += speedY;

                // Controllo dei bordi e inversione direzione
                if (positionX <= 0 || positionX + 50 >= screenWidth) {
                    speedX *= -1;
                }

                if (positionY <= 0 || positionY + 50 >= screenHeight) {
                    speedY *= -1;
                }

                dvdData.positionX = positionX;
                dvdData.positionY = positionY;
                dvdData.speedX = speedX;
                dvdData.speedY = speedY;

                // Applica la trasformazione
                dvdData.element.style.transform = `translate(${positionX}px, ${positionY}px)`;
            });

            requestAnimationFrame(animateDVDs);
        };

        animateDVDs();
    }, []);

    const redirectToGitHub = () => {
        window.location.href = "https://github.com/IvanComp/DOLLY";
    };

    const bpmnImages = [
        img0,
        img1,
        img2,
        img3,
        img4,
        img5,
        img6,
        img7,
        img8,
        img9,
        img10,
        img11,
        img12,
    ].map((src, index) => (
        <img
            key={index}
            className="dvdLogo"
            src={src}
            alt={`BPMN object ${index}`}
        />
    ));

    return <div className="dvd-container">
        <div className="background-blur"></div>
<button
    className="centered-button"
    onClick={redirectToGitHub}
    style={{
        backgroundColor: "#fafaff",
        color: "#1C2950",
        position: "fixed",
        padding: '10px 20px',
        borderRadius: '15px',
        cursor: 'pointer',
        marginLeft: "30%",
        marginTop: "20%",
        zIndex: 100,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        gap: "10px", // Spaziatura tra icone
    }}
>
    <BsGithub style={{ height: "50px", width: "50px" }} />
    <img
        style={{ width: "60px", height: "60px" }}
        src={developer}
        alt="Animated icons by Lordicon.com"
    />
</button>
        {bpmnImages}</div>;
    
};

export default DVDPlayer;