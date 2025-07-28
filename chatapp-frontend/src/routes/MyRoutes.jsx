import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../pages/Login";

const router = createBrowserRouter([{ path: "/", element: <Login /> }]);

export default function MyRoutes() {
  return <RouterProvider router={router} />;
}
