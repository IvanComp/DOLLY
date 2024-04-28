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

type MenuRoute = ViewRouteObject &
  Readonly<{
    path: string;
    handle: Required<MenuProps>;
  }>;

export default function MenuOnLeftLayout() {
  const matches = useViewMatches();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isAPIOnline, setIsAPIOnline] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:4567');
      setIsAPIOnline(response.status === 200);
    } catch (error) {
      setIsAPIOnline(false);
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
        <div style={{marginLeft:'1%',position: 'absolute', top:"515px", height:"13%", width: "90%", backgroundColor: "#EAF6FF", border: "2px solid black", borderRadius: "10px", textAlign: "left", margin: "auto" }}>
          <a style={{marginLeft:'3%',fontWeight:"bold", color:'#334F6D',top:"10px",bottom:'10px'}}>Settings </a>
          <br/>
          <div style={{ display: 'flex', alignItems: 'left' }}>
          <a style={{fontWeight:"bold", marginLeft:"5%", color:'#154A57'}}>API Status:</a><a style={{marginLeft:"13%"}}><div>
              {isAPIOnline ? (
                  <div>
          <span style={{ fontWeight: 'normal', color: 'black' }}>
           <a href="http://localhost:4567" target="_blank">
              Online <div className="online-dot"></div>
           </a>
          </span>
                  </div>
              ) : (
                  <div>
          <span style={{ fontWeight: 'normal', color: 'black'}}>
            Offline <div className="offline-dot" style={{marginLeft:"0px"}}></div>
          </span>
                  </div>
              )}
        </div></a><CiCircleQuestion style={{fontSize:'18px',marginBottom:"3%",cursor:"help"}} title={"This is the status of the API Orchestrator for Domain Model Instances"}/>
          </div>
          <div style={{ display: 'flex', alignItems: 'left' }}>
          <a style={{fontWeight:"bold",marginLeft:"5%", color:'#154A57',marginRight:"9%"}}>BPM Engine:</a><div>
            {isAPIOnline ? (
                <div>
          <span style={{ fontWeight: 'normal', color: 'black' }}>
            Online <div className="online-dot"></div>
          </span>
                </div>
            ) : (
                <div>
          <span style={{ fontWeight: 'normal', color: 'black'}}>
            Offline <div className="offline-dot" style={{marginLeft:"0px"}}></div>
          </span>
                </div>
            )}
        </div><CiCircleQuestion style={{fontSize:'18px',marginBottom:"3%",cursor:"help"}} title={"This is the status of the BPM Engine"}/>
          </div>
        </div>

        <div style={{position: 'absolute', bottom: '5px', left: '0', width: '100%', margin: '0 auto'}}>
          <hr style={{color: 'red', backgroundColor:'#5b5b65', border:'none', height: '1px', margin: '5px 5%', width: '90%'}} />
          <div style={{textAlign: 'center'}}>
            <p style={{fontSize:"15px", color: '#eae9e9"', margin: '0'}}>Version: 1.0.0</p>
            <p style={{ fontSize: '16px', marginBottom: '2%', marginTop: '0'}}>
              <HiAcademicCap style={{ marginBottom: '0.1cm', fontSize: '18px' }} />
              <a
                  style={{ marginLeft: '1%', fontSize: '14px', marginRight: '10%',  color: "#005fdb", cursor: "pointer"}}
                  onClick={openModal}
              >
                References
              </a>
              <MdAlternateEmail style={{ marginBottom: '0.1cm' }} />
              <a style={{ marginLeft: '1%', fontSize: '14px' }} href="mailto:ivan.compagnucci@unicam.it">
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
              <h2 style={{top:"10px"}}><HiAcademicCap style={{marginBottom: '0.13cm', marginRight:"2%"}}/>References</h2>

              <p>Tool's paper:</p>

              <ul>
                <li>I. Compagnucci, M. Snoeck and E. S. Asensio, "Supporting Digital Twins Systems Integrating the MERODE Approach," 2023 ACM/IEEE International Conference on Model Driven Engineering Languages and Systems Companion (MODELS-C), Västerås, Sweden, 2023, pp. 449-458, doi: 10.1109/MODELS-C59198.2023.00079.</li>
              </ul>
              <button style={{color: 'white',backgroundColor:"rgb(51, 79, 109)", fontSize: '15px', padding: '10px 10px', cursor: 'pointer', marginTop: '0.42cm'}} onClick={closeModal}>Close</button>
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
