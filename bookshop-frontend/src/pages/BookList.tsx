import { useEffect, useState } from "react";
import axios from "axios";
import Header from "../components/Header";
import BookTable from "../components/BookTable";
import Pagination from "../components/Pagination";
import AddBookModal from "@/components/AddBookModal";

interface Book {
  id: number;
  title: string;
  author: string;
  gender: string;
  year: number;
  isRented: boolean;
}

function BookList() {
  const [books, setBooks] = useState<Book[]>([]);
  const [filters, setFilters] = useState({
    title: "",
    author: "",
    gender: "",
    year: "",
  });
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [showModal, setShowModal] = useState(false);

  const fetchBooks = async () => {
    const token = localStorage.getItem("token");
    try {
      const params: Record<string, string | number> = {
        page: currentPage,
        size: 10,
      };

      Object.entries(filters).forEach(([key, value]) => {
        if (value) params[key] = value;
      });

      const res = await axios.get("http://localhost:8080/books", {
        params,
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setBooks(res.data.content);
      setTotalPages(res.data.totalPages);
    } catch {
      alert("Erro ao buscar livros");
    }
  };

  useEffect(() => {
    fetchBooks();
  }, [currentPage]);

  const handleFilter = () => {
    setCurrentPage(0);
    fetchBooks();
  };

  const handleDelete = async (id: number) => {
    const token = localStorage.getItem("token");
    const confirm = window.confirm(
      "Tem certeza que deseja excluir este livro?"
    );
    if (!confirm) return;

    try {
      await axios.delete(`http://localhost:8080/books/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      fetchBooks();
    } catch {
      alert("Erro ao excluir livro");
    }
  };

  return (
    <div className="p-6">
      <Header
        filters={filters}
        setFilters={setFilters}
        onFilter={handleFilter}
        onAddBook={() => setShowModal(true)}
      />
      <BookTable books={books} onDelete={handleDelete} />
      <Pagination
        totalPages={totalPages}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />

      <AddBookModal
        isOpen={showModal}
        onClose={() => setShowModal(false)}
        onBookAdded={() => {
          setShowModal(false);
          fetchBooks();
        }}
      />
    </div>
  );
}

export default BookList;
