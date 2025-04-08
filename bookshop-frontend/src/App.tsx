import { Routes, Route } from "react-router-dom";
import PrivateRoute from "./routes";
import Login from "./pages/Login";
import BookList from "./pages/BookList";
import BookDetails from "./pages/BookDetails";

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
      <Route path="/books/:id" element={<BookDetails />} />
    </Routes>
  );
}

export default App;
