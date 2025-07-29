import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import ChatLayout from "../layout/ChatLayout";

const router = createBrowserRouter([
  {
    path: "/",
    element: <ChatLayout />,
    children: [
      // {path:"", element: }
    ],
  },
  { path: "/login", element: <Login /> },
  { path: "/register", element: <Register /> },
]);

export default function AppRoutes() {
  return <RouterProvider router={router} />;
}
