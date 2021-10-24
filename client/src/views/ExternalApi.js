import React from "react";
import { Button } from "reactstrap";
import { useAuth0, withAuthenticationRequired } from "@auth0/auth0-react";
import configJson from "../config/auth_config.json";
import Loading from "../components/Loading";

export const ExternalApiComponent = () => {
  const { getAccessTokenSilently } = useAuth0();

  const callApi = async () => {
    const token = await getAccessTokenSilently();
    console.log(token);

    await fetch(`${configJson.apiOrigin}/books`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  };

  return (
    <>
      <div className="mb-5">
        <h1>External API</h1>
        <p className="lead">
          Ping an external API by clicking the button below.
        </p>

        <Button color="primary" className="mt-5" onClick={callApi}>
          Ping API
        </Button>
      </div>
    </>
  );
};

export default withAuthenticationRequired(ExternalApiComponent, {
  onRedirecting: () => <Loading />,
});
