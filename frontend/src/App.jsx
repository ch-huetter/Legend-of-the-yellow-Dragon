import CharacterSelection from './components/CharacterSelection.jsx'
import {MainLayout} from "./layout/MainContext.jsx";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {Login} from "./sites/Login.jsx";

function App() {
    return (<BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/game" element={<MainLayout/>}>
                    <Route path="characters" element={<CharacterSelection/>}/>
                </Route>

            </Routes>
        </BrowserRouter>
    )
}

export default App
