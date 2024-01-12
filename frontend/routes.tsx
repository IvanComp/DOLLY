import MainLayout from 'Frontend/views/MainLayout.js';
import 'line-awesome/dist/line-awesome/css/line-awesome.min.css';
import { lazy } from 'react';
import { createBrowserRouter, IndexRouteObject, NonIndexRouteObject, useMatches } from 'react-router-dom';
import ServicesView from "Frontend/views/microservice/ServicesView.js";
import { MdOutlineMiscellaneousServices } from "react-icons/md";
import DashboardView from "Frontend/views/dashboard/DashboardView.js";
import ThreeDee from "Frontend/views/3d/ThreeDee.js";

const AboutView = lazy(async () => import('Frontend/views/about/AboutView.js'));

export type MenuProps = Readonly<{
  icon?: React.ReactNode;  // Usiamo React.ReactNode invece di string per consentire l'utilizzo di componenti React come icone
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
    handle: { icon: null, title: 'Main' },
    children: [
      { path: '/', element: <DashboardView />, handle: { icon: <i className="la la-star"></i>, title: 'Digital Twin Dashboard' } },
      { path: '/services', element: <ServicesView />, handle: { icon: <MdOutlineMiscellaneousServices />, title: 'Real-World Services' } },
      { path: '/about', element: <AboutView />, handle: { icon: <i className="la la-globe"></i>, title: 'About' } },
      { path: '/3d', element: <ThreeDee /> },
    ],
  },
];

const router = createBrowserRouter([...routes]);
export default router;
