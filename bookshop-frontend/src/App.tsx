import { Routes, Route } from "react-router-dom";
import PrivateRoute from "./routes";
import BookDetail from "./pages/Login";
import Login from "./pages/Login";
import BookList from "./pages/BookList";

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route
        path="/"
        element={
          <PrivateRoute>
            <BookList />
          </PrivateRoute>
        }
      />
      <Route
        path="/books/:id"
        element={
          <PrivateRoute>
            <BookDetail />
          </PrivateRoute>
        }
      />
    </Routes>
  );
}

export default App;
