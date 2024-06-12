import { Route, Routes } from "react-router-dom";
import "./App.css";
import UserDataPage from "./assets/users/UserDataPage";
import MainPage from "./assets/base/MainPage";
import NotFoundPage from "./assets/base/NotFoundPage";
import "bootstrap/dist/css/bootstrap.min.css";
import BookDataPage from "./assets/books/BookDataPage";
import NavBar from "./assets/base/NavBar";

function App() {
  return (
    <>
        <NavBar />
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/books" element={<BookDataPage />} />
          <Route path="/users" element={<UserDataPage />} />
          <Route path="/*" element={<NotFoundPage />} />
        </Routes>
    </>
  );
}

export default App;
