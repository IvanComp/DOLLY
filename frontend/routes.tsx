import MainLayout from 'Frontend/views/MainLayout.js';
import 'line-awesome/dist/line-awesome/css/line-awesome.min.css';
import { lazy } from 'react';
import { createBrowserRouter, IndexRouteObject, NonIndexRouteObject, useMatches } from 'react-router-dom';
import ServicesView from "Frontend/views/microservice/ServicesView.js";
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
      { path: '/services', element: <ServicesView />, handle: { icon: 'la la-tachometer', title: 'Digital Twin Services' } },
      { path: '/about', element: <AboutView />, handle: { icon: 'la la-info', title: 'About' } },
    ],
  },
];

const router = createBrowserRouter([...routes]);
export default router;
