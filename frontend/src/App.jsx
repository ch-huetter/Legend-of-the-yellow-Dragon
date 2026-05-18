import {CharacterSelection} from './sites/mainContext/CharacterSelection.jsx'
import {MainLayout} from "./layout/MainLayout.jsx";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {Logout} from "./sites/Logout.jsx";
import {initializeGlobalValues} from "./service/GlobalContext.js";
import {Tavern} from "./sites/mainContext/Tavern.jsx";
import {Login} from "./sites/Login.jsx";


function App() {

    initializeGlobalValues();

    return (<BrowserRouter>
            <Routes>
                <Route index element={<Login/>}/>
                <Route path="/logout" element={<Logout/>}/>
                <Route path="/game" element={<MainLayout/>}>
                    <Route path="characterSelection" element={<CharacterSelection/>}/>
                    <Route path="tavern" element={<Tavern/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App
