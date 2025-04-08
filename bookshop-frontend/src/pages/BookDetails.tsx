import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "sonner";

interface Book {
  id: number;
  title: string;
  author: string;
  gender: string;
  year: number;
  isRented: boolean;
}

function BookDetails() {
  const { id } = useParams<{ id: string }>();
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
      console.log("Erro ao buscar o livro", error);
      toast.error("Erro ao buscar o livro.");
    }
  };

  const handleRent = async () => {
    try {
      await axios.patch(
        `http://localhost:8080/books/${id}/rent`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      alert("Livro alugado com sucesso!");
      fetchBook();
    } catch (error) {
      console.log("Erro ao buscar o livro", error);
      toast.error("Erro ao alugar o livro.");
    }
  };

  const handleReturn = async () => {
    try {
      await axios.patch(
        `http://localhost:8080/books/${id}/unrent`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      alert("Livro devolvido com sucesso!");
      fetchBook();
    } catch (error) {
      console.log("Erro ao buscar o livro", error);
      toast.error("Erro ao devolver o livro.");
    }
  };

  useEffect(() => {
    fetchBook();
  }, []);

  if (!book) {
    return <div className="text-center mt-10">Carregando detalhes...</div>;
  }

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md max-w-md w-full">
        <h1 className="text-2xl font-bold mb-6">Detalhes do Livro</h1>

        <p>
          <span className="font-semibold">Título:</span> {book.title}
        </p>
        <p>
          <span className="font-semibold">Autor:</span> {book.author}
        </p>
        <p>
          <span className="font-semibold">Gênero:</span> {book.gender}
        </p>
        <p>
          <span className="font-semibold">Ano:</span> {book.year}
        </p>
        <div className="mt-6 flex justify-start gap-4">
          {book.isRented ? (
            <button
              onClick={handleReturn}
              className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600"
            >
              Devolver
            </button>
          ) : (
            <button
              onClick={handleRent}
              className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
            >
              Alugar
            </button>
          )}
          <button
            onClick={() => navigate("/")}
            className="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400"
          >
            Voltar
          </button>
        </div>
      </div>
    </div>
  );
}

export default BookDetails;
