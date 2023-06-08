import HelloReactView from 'Frontend/views/helloreact/HelloReactView.js';
import MainLayout from 'Frontend/views/MainLayout.js';
import 'line-awesome/dist/line-awesome/css/line-awesome.min.css';
import { lazy } from 'react';
import { createBrowserRouter, IndexRouteObject, NonIndexRouteObject, useMatches } from 'react-router-dom';
import MicroservicesView from "Frontend/views/microservice/MicroservicesView.js";
import {MdOutlineMiscellaneousServices} from "react-icons/md";
import DashboardView from "Frontend/views/dashboard/DashboardView.js";

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
    handle: { icon: 'null', title: 'Main' },
    children: [
      { path: '/', element: <DashboardView />, handle: { icon: 'la la-tachometer', title: 'Dashboard' } },
      { path: '/model', element: <HelloReactView />, handle: { icon: 'la la-file-code-o', title: 'Microservice' } },
      { path: '/microservices', element: <MicroservicesView />, handle: { icon: 'la la-tachometer', title: 'List of Devices' } },
      { path: '/about', element: <AboutView />, handle: { icon: 'la la-info', title: 'About' } },
    ],
  },
];

const router = createBrowserRouter([...routes]);
export default router;
