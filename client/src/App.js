import "./styles.css";
import Home from "./pages/Home";
import Book from "./pages/Book";
import Navbar from "./pages/Navbar";
import { BrowserRouter, Route, Switch } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Switch>
          <Route path="/" exact render={() => <Home />} />
          <Route path="/:id" render={() => <Book />} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
