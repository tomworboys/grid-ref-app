import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
    const [count, setCount] = useState(0);
    const [gridReferences, setGridReferences] = useState([]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/generate?count=${count}`);
            if (response.ok) {
                const data = await response.json();
                setGridReferences(data);
            } else {
                console.error('Failed to generate grid references');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="container mt-5">
            <h1 className="text-center">Generate Grid References</h1>
            <form onSubmit={handleSubmit} className="mt-4">
                <div className="form-group">
                    <label htmlFor="count">Count:</label>
                    <input
                        type="number"
                        className="form-control"
                        id="count"
                        value={count}
                        onChange={(e) => setCount(e.target.value)}
                    />
                </div>
                <button type="submit" className="btn btn-primary mt-3">Generate</button>
            </form>
            {gridReferences.length > 0 && (
                <div className="mt-4">
                    <h2>Generated Grid References:</h2>
                    <ul>
                        {gridReferences.map((ref, index) => (
                            <li key={index}>{ref}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default App;