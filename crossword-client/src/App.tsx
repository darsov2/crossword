import './App.css'
import {BrowserRouter, Route, Routes} from "react-router";
import HomePage from "@/pages/HomePage/HomePage.tsx";
import RegisterLoginPage from "@/pages/RegisterLoginPage/RegisterLoginPage.tsx";
import TodaysCrosswordPage from "@/pages/TodaysCrosswordPage/TodaysCrosswordPage.tsx";
import PreviousDaysCrosswordsPage from "@/pages/PreviousDaysCrosswords/PreviousDaysCrosswordsPage.tsx";

function App() {

    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/login" element={<RegisterLoginPage />} />
                    <Route path="/todays" element={<TodaysCrosswordPage />} />
                    <Route path="/previous" element={<PreviousDaysCrosswordsPage />} />
                </Routes>
            </BrowserRouter>
        </>
    )
}

export default App
