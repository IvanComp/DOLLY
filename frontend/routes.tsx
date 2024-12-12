import MainLayout from 'Frontend/views/MainLayout.js';
import { lazy } from 'react';
import { createBrowserRouter, IndexRouteObject, NonIndexRouteObject, useMatches } from 'react-router-dom';
import ServicesView from "Frontend/views/microservice/ServicesView.js";
import DashboardView from "Frontend/views/dashboard/DashboardView.js";
import ThreeDee from "Frontend/views/3d/ThreeDee.js";
import Monitoring from "Frontend/views/monitoring/Monitoring.js";
import { TbDeviceHeartMonitorFilled } from "react-icons/tb";
import { FaGithub, FaGlobe } from "react-icons/fa";  // Importing icons from react-icons
import Simulation from "Frontend/views/simulation/Simulation.js";
import 'line-awesome/dist/line-awesome/css/line-awesome.min.css';
import {HiAcademicCap} from "react-icons/hi";
import {MdAlternateEmail} from "react-icons/md";
import Readme from "Frontend/views/readme/Readme.js";
// @ts-ignore
import Home from "Frontend/views/home/Home.js";

const AboutView = lazy(async () => import('Frontend/views/about/AboutView.js'));

export type MenuProps = Readonly<{
    icon?: string;
    title?: string;
}>;

export type ViewMeta = Readonly<{ handle?: MenuProps }>;

type Override<T, E> = Omit<T, keyof E> & E;

export type IndexViewRouteObject = Override<IndexRouteObject, ViewMeta>;
export type NonIndexViewRouteObject = Override<
    Override<NonIndexRouteObject, ViewMeta>,
    {
        children?: ViewRouteObject[];
    }
>;
export type ViewRouteObject = IndexViewRouteObject | NonIndexViewRouteObject;

type RouteMatch = ReturnType<typeof useMatches> extends (infer T)[] ? T : never;

export type ViewRouteMatch = Readonly<Override<RouteMatch, ViewMeta>>;

export const useViewMatches = useMatches as () => readonly ViewRouteMatch[];

export const routes: readonly ViewRouteObject[] = [
    {
        element: <MainLayout />,
        handle: { icon: undefined, title: 'Main' },
        children: [
            { path: '/', element: <Home />, handle: { title: 'Home Page' }},
            { path: '/domaindata', element: <DashboardView />, handle: { icon: 'broadcast-tower-solid', title: 'Domain Data Modeling' }},
            { path: '/bpmn', element: <ServicesView />, handle: { icon: 'sitemap-solid', title: 'Process Modeling' }},
            { path: '/simulation', element: <Simulation />, handle: { icon: 'chalkboard-solid', title: 'BPMN Simulation' }},
            { path: '/about', element: <AboutView />, handle: { icon: 'github', title: 'About' }},
            { path: '/3d', element: <ThreeDee /> },
        ],
    },
];

const router = createBrowserRouter([...routes]);
export default router;
