import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

interface Book {
  id: number;
  title: string;
  author: string;
  gender: string;
  year: number;
  isRented: boolean;
}

function BookDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState<Book | null>(null);

  const token = localStorage.getItem("token");

  const fetchBook = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/books/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setBook(res.data);
    } catch (error) {
      console.error("Erro ao buscar o livro:", error);
      alert("Erro ao buscar o livro");
    }
  };

  const toggleRental = async () => {
    try {
      const endpoint = book?.isRented
        ? `http://localhost:8080/books/${id}/unrent`
        : `http://localhost:8080/books/${id}/rent`;

      console.log("Endpoint", endpoint);

      await axios.patch(endpoint, null, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      fetchBook();
    } catch (error) {
      console.error("Erro ao buscar o livro:", error);
      alert("Erro ao atualizar status do livro");
    }
  };

  useEffect(() => {
    fetchBook();
  }, []);

  if (!book) return <div className="p-6">Carregando...</div>;

  return (
    <div className="p-6 max-w-xl mx-auto bg-white rounded shadow">
      <h1 className="text-2xl font-bold mb-4">Detalhes do Livro</h1>
      <p>
        <strong>Título:</strong> {book.title}
      </p>
      <p>
        <strong>Autor:</strong> {book.author}
      </p>
      <p>
        <strong>Gênero:</strong> {book.gender}
      </p>
      <p>
        <strong>Ano:</strong> {book.year}
      </p>
      <p>
        <strong>Status:</strong> {book.isRented ? "Alugado" : "Disponível"}
      </p>

      <div className="mt-6 flex gap-4">
        <button
          onClick={toggleRental}
          className={`px-4 py-2 rounded text-white ${
            book.isRented
              ? "bg-yellow-500 hover:bg-yellow-600"
              : "bg-green-500 hover:bg-green-600"
          }`}
        >
          {book.isRented ? "Devolver" : "Alugar"}
        </button>

        <button
          onClick={() => navigate("/")}
          className="px-4 py-2 bg-gray-300 hover:bg-gray-400 rounded"
        >
          Voltar
        </button>
      </div>
    </div>
  );
}

export default BookDetails;
