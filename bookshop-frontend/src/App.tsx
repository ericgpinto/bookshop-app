import { Routes, Route } from "react-router-dom";
import PrivateRoute from "./routes";
import Books from "./pages/Books";
import BookDetail from "./pages/Login";
import Login from "./pages/Login";

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route
        path="/"
        element={
          <PrivateRoute>
            <Books />
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
