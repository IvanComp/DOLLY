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

type MenuRoute = ViewRouteObject &
  Readonly<{
    path: string;
    handle: Required<MenuProps>;
  }>;

export default function MenuOnLeftLayout() {
  const matches = useViewMatches();

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

        <div style={{position: 'absolute', bottom: '5px', left: '0', width: '100%', margin: '0 auto'}}>
          <hr style={{color: 'red', backgroundColor:'#5b5b65', border:'none', height: '1px', margin: '5px 5%', width: '90%'}} />
          <div style={{textAlign: 'center'}}>
            <p style={{fontSize:"15px", color: '#eae9e9"', margin: '0'}}>Version: 1.0.0</p>
            <p style={{ fontSize: '16px', marginBottom: '2%', marginTop: '0' }}>
              <HiAcademicCap style={{ marginBottom: '0.1cm', fontSize: '18px' }} />
              <a
                  style={{ marginLeft: '1%', fontSize: '14px', marginRight: '10%',  color: "#005fdb", cursor: "pointer"}}

              >
                References
              </a>
              <MdAlternateEmail style={{ marginBottom: '0.1cm' }} />
              <a style={{ marginLeft: '1%', fontSize: '14px' }} href="mailto:ivan.compagnucci@unicam.it">
                Contact
              </a>
            </p>

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
