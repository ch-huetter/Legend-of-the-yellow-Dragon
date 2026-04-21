import React, {useEffect, useState} from "react"
import {fetchCharactersSortedByActiveThenLevel, postNewActiveCharacterName} from "../api/characterApi.js";

function CharacterSelection() {
    const [characters, setCharacters] = useState([]);

    useEffect(() => {


        getCharacters().then(r => setCharacters(r));

    }, [])

    async function getCharacters() {
        return await fetchCharactersSortedByActiveThenLevel();
    }

    return (
        <div className="leg-flex-row leg-flex-wrap leg-align-flex-start">
            {characters.map((character, index) =>
                <div key={character.name} className="leg-item-half">
                    <form className="leg-box leg-h100">
                        <div className="leg-flex-row">
                            <div className="leg-flex-column">
                                <div>
                                    <p>Name : {character.name}</p>
                                </div>
                                <div>
                                    <p>Level : {character.level}</p>
                                    <p>Gold : {character.gold}</p>
                                    <button onClick={(e) => {
                                        e.preventDefault();
                                        postNewActiveCharacterName(character.name).then(() => {
                                            getCharacters().then(r => setCharacters(r))
                                        });
                                    }}>
                                        Choose
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            )}
        </div>
    )
}

//style="background: url(/images/background/login_background.png&quot;);
export default CharacterSelection;
