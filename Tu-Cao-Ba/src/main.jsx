import * as React from "react";
import { createRoot } from "react-dom/client";
import App from "./layout/Index";

import "./assets/css/main.css";

import { NextUIProvider } from "@nextui-org/react";

createRoot(document.getElementById("root")).render(
  <NextUIProvider>
    <App />
  </NextUIProvider>
);
