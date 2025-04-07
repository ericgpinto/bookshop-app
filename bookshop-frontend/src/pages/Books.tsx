import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

interface Book {
  id: number;
  title: string;
  author: string;
  gender: string;
  year: number;
  isRented: boolean;
}

function Books() {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [gender, setGender] = useState("");
  const [year, setYear] = useState("");
  const navigate = useNavigate();

  const fetchBooks = async () => {
    setLoading(true);
    try {
      const token = localStorage.getItem("token");
      const params = new URLSearchParams();
      if (title) params.append("title", title);
      if (author) params.append("author", author);
      if (gender) params.append("gender", gender);
      if (year) params.append("year", year);

      const response = await axios.get(
        `http://localhost:8080/books?${params.toString()}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setBooks(response.data.content);
    } catch (err) {
      console.error("Erro ao buscar livros:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Lista de Livros</h1>

      {}
      <div className="mb-6 flex flex-col md:flex-row gap-4">
        <input
          type="text"
          placeholder="Título"
          className="border border-gray-300 rounded px-3 py-2"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          type="text"
          placeholder="Autor"
          className="border border-gray-300 rounded px-3 py-2"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
        />
        <input
          type="text"
          placeholder="Gênero"
          className="border border-gray-300 rounded px-3 py-2"
          value={gender}
          onChange={(e) => setGender(e.target.value)}
        />
        <input
          type="number"
          placeholder="Ano"
          className="border border-gray-300 rounded px-3 py-2"
          value={year}
          onChange={(e) => setYear(e.target.value)}
        />
        <button
          onClick={fetchBooks}
          className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded shadow"
        >
          Buscar
        </button>
      </div>

      {}
      {loading ? (
        <div className="text-center">Carregando livros...</div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border border-gray-200 rounded-lg">
            <thead>
              <tr className="bg-gray-100 text-left">
                <th className="p-3 border-b">Título</th>
                <th className="p-3 border-b">Autor</th>
                <th className="p-3 border-b">Gênero</th>
                <th className="p-3 border-b">Ano</th>
                <th className="p-3 border-b">Alugado?</th>
                <th className="p-3 border-b">Ações</th>
              </tr>
            </thead>
            <tbody>
              {books.map((book) => (
                <tr key={book.id} className="hover:bg-gray-50">
                  <td className="p-3 border-b">{book.title}</td>
                  <td className="p-3 border-b">{book.author}</td>
                  <td className="p-3 border-b">{book.gender}</td>
                  <td className="p-3 border-b">{book.year}</td>
                  <td className="p-3 border-b">
                    {book.isRented ? "Sim" : "Não"}
                  </td>
                  <td className="p-3 border-b">
                    <button
                      onClick={() => navigate(`/books/${book.id}`)}
                      className="text-sm bg-blue-600 hover:bg-blue-700 text-white py-1 px-3 rounded shadow"
                    >
                      Detalhes
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default Books;
