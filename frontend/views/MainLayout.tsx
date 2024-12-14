import { AppLayout } from '@hilla/react-components/AppLayout.js';
import { DrawerToggle } from '@hilla/react-components/DrawerToggle.js';
import { Item } from '@hilla/react-components/Item.js';
import { Scroller } from '@hilla/react-components/Scroller.js';
import Placeholder from 'Frontend/components/placeholder/Placeholder.js';
import { MenuProps, routes, useViewMatches, ViewRouteObject } from 'Frontend/routes.js';
import { NavLink, Outlet } from 'react-router-dom';
import css from './MainLayout.module.css';
import logo from "./../img/logo.png"
import {HiAcademicCap} from "react-icons/hi";
import {MdAlternateEmail} from "react-icons/md";
import Modal from 'react-modal';
import React, {Suspense, useEffect, useState} from 'react';
import {CiCircleQuestion} from "react-icons/ci";
import axios from "axios";
import {FaBug} from "react-icons/fa";

type MenuRoute = ViewRouteObject &
  Readonly<{ path: string; handle: Required<MenuProps>; }>;

export default function MenuOnLeftLayout() {
  const matches = useViewMatches();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isAPIOnline, setIsAPIOnline] = useState(false);
  const [isBPMOnline, setIsBPMOnline] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };

  const fetchData = async () => {
    try {
      const responseAPI = await axios.get('http://localhost:4567/platform');
      setIsAPIOnline(responseAPI.status === 200);
    } catch (error) {
      setIsAPIOnline(false);
    }
  
    try {
      const responseBPM = await axios.get('http://localhost:8888/engine-rest/engine');
      setIsBPMOnline(responseBPM.status === 200);
    } catch (error) {
      setIsBPMOnline(false);
    }
  };
  
  useEffect(() => {
    fetchData(); // Esegui la chiamata iniziale al montaggio del componente

    const pollingInterval = setInterval(async () => {
      await fetchData(); // Esegui la chiamata periodicamente
    }, 2000); // Ogni 5 secondi (puoi regolare l'intervallo secondo le tue esigenze)

    // Pulisci l'intervallo quando il componente viene smontato
    return () => clearInterval(pollingInterval);
  }, []);

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const currentTitle = matches[matches.length - 1]?.handle?.title ?? 'Unknown';

  const menuRoutes = (routes[0]?.children || []).filter(
    (route) => route.path && route.handle && route.handle.icon && route.handle.title
  ) as readonly MenuRoute[];

    return (
    <AppLayout className="block h-full" primarySection="drawer">
      <header slot="drawer">
          <h1 className="text-l m-0 center"><img style={{marginLeft:"4%", marginTop:"4%"}} src={logo} alt="" width="200"/></h1>
      </header>
      <Scroller slot="drawer" scroll-direction="vertical">
        <nav>
          {menuRoutes.map(({ path, handle: { icon, title } }) => (
            <NavLink
              className={({ isActive }) => `${css.navlink} ${isActive ? css.navlink_active : ''}`}
              key={path}
              to={path}
            >
              {({ isActive }) => (
                <Item key={path} selected={isActive}>
                  <span
                    className={css.navicon}
                    style={{ '--mask-image': `url('line-awesome/svg/${icon}.svg')` } as any}
                    aria-hidden="true"
                  ></span>
                  {title}
                </Item>
              )}
            </NavLink>
          ))}
        </nav>

        <div
  style={{
    position: "relative",
    margin: "15px auto",
    padding: "5px",
    height: "auto",
    width: "80%",
    backgroundColor: "#EAF6FF",
    border: "2px solid black",
    borderRadius: "10px",
    textAlign: "left",
  }}
>
  <h3 style={{ fontWeight: "bold", color: "#334F6D", marginBottom: "5px" }}>
    Settings
  </h3>

  {/* API Status */}
  <div style={{ display: "flex", alignItems: "center", marginBottom: "3px" }}>
    <span style={{ marginLeft:"5px", fontWeight: "bold", color: "#154A57", flex: "0 0 60%",fontSize: "18px" }}>
      Data Model:
    </span>
    <span style={{ flex: "1 0 auto" }}>
      {isAPIOnline ? (
        <a
          href="http://localhost:4567"
          target="_blank"
          style={{
            textDecoration: "none",
            color: "green",
            fontWeight: "bold",
          }}
        >
          Online
        </a>
      ) : (
        <span style={{ color: "red", fontWeight: "bold" }}>
          Offline 
        </span>
      )}
    </span>
  </div>

  {/* BPM Engine Status */}
  <div style={{ display: "flex", alignItems: "center" }}>
    <span style={{ marginLeft:"5px", fontWeight: "bold", color: "#154A57", flex: "0 0 60%", fontSize: "18px" }}>
      BPM Engine:
    </span>
    <span style={{ flex: "1 0 auto" }}>
      {isBPMOnline ? (
        <span style={{ color: "green", fontWeight: "bold" }}>
          Online 
        </span>
      ) : (
        <span style={{ color: "red", fontWeight: "bold" }}>
          Offline 
        </span>
      )}
    </span>
  </div>
</div>

        <div style={{position: 'absolute', bottom: '5px', left: '0', width: '100%', margin: '0 auto'}}>
          <hr style={{color: 'red', backgroundColor:'#5b5b65', border:'none', height: '1px', margin: '5px 5%', width: '90%'}} />
          <div style={{textAlign: 'center'}}>
            <p style={{fontSize:"15px", color: '#eae9e9"', margin: '0'}}>Version: 1.0.0</p>
            <p style={{ fontSize: '16px', marginBottom: '2%', marginTop: '0' }}>
              <HiAcademicCap style={{ verticalAlign: 'middle', fontSize: '18px', marginBottom:"2px", marginRight:"3px"  }} />
              <a
                  style={{ marginLeft: '1%', fontSize: '14px', marginRight: '10%',  color: "#005fdb", cursor: "pointer"}}
                  onClick={openModal}
              >
                References
              </a>
              <FaBug style={{ verticalAlign: 'middle',marginBottom:"1px", marginRight:"3px" }} />
              <a style={{ marginLeft: '1%', fontSize: '14px',  }} href="mailto:ivan.compagnucci@gmail.com">
                Report a Bug
              </a>
              <br/>
              <MdAlternateEmail style={{ verticalAlign: 'middle', marginBottom:"2px", marginRight:"3px"  }} />
              <a style={{ marginLeft: '1%', fontSize: '14px' }} href="mailto:ivan.compagnucci@gmail.com">
                Contact
              </a>
            </p>


            <Modal
                style={{
                  overlay: {
                    backgroundColor: 'rgba(255, 255, 255, 0.67)', // Sfondo trasparente
                    zIndex: 2 // Imposta un valore di z-index superiore rispetto al bottone
                  },
                  content: {
                    width: '750px',
                    height: '300px',
                    margin: 'auto',
                    borderRadius: '8px',
                    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.5)',
                    zIndex: 2 // Imposta un valore di z-index superiore rispetto al bottone
                  }
                }}
                isOpen={isModalOpen}
                onRequestClose={closeModal}
            >

                    <h2 style={{ display: "flex", alignItems: "center", margin: 0, top: "10px", position: "relative" }}>
                      <HiAcademicCap style={{ marginBottom: '0.13cm', marginRight: "2%", flexShrink: 0 }}/>
                      References
                    </h2>

              <ul>
                <li>I. Compagnucci, M. Snoeck and E. S. Asensio, "Supporting Digital Twins Systems Integrating the MERODE Approach," 2023 ACM/IEEE International Conference on Model Driven Engineering Languages and Systems Companion (MODELS-C), Västerås, Sweden, 2023, pp. 449-458, doi: 10.1109/MODELS-C59198.2023.00079.</li>
              </ul>
              <button style={{color: '#324e6c',backgroundColor:"#aad4de", fontSize: '15px', padding: '10px 10px', cursor: 'pointer', marginTop: '0.42cm'}} onClick={closeModal}>Close</button>
            </Modal>

          </div>
        </div>
      </Scroller>
      <footer slot="drawer" />

      <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
      <h2 slot="navbar" className="text-l m-0">
        {currentTitle}
      </h2>


      <Suspense fallback={<Placeholder />}>
        <Outlet />
      </Suspense>
    </AppLayout>


  );

}
