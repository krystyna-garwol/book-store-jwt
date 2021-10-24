import React from "react";
import ReactDOM from "react-dom";
import "./css/index.css";
import App from "./App";
import { Auth0Provider } from "@auth0/auth0-react";
import configJson from "./config/auth_config.json";

const providerConfig = {
  domain: configJson.domain,
  clientId: configJson.clientId,
  ...(configJson.audience ? configJson : null),
  redirectUri: window.location.origin,
};

ReactDOM.render(
  <Auth0Provider {...providerConfig}>
    <App />
  </Auth0Provider>,
  document.getElementById("root")
);
