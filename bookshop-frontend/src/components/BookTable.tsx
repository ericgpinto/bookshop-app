// src/components/BookTable.tsx
import { useNavigate } from "react-router-dom";

interface Book {
  id: number;
  title: string;
  author: string;
  gender: string;
  year: number;
  isRented: boolean;
}

interface BookTableProps {
  books: Book[];
  onDelete: (id: number) => void;
}

function BookTable({ books, onDelete }: BookTableProps) {
  const navigate = useNavigate();

  return (
    <div className="overflow-x-auto mt-6">
      <table className="min-w-full table-auto border border-gray-300">
        <thead className="bg-gray-100">
          <tr>
            <th className="px-4 py-2 text-left">Título</th>
            <th className="px-4 py-2 text-left">Autor</th>
            <th className="px-4 py-2 text-left">Gênero</th>
            <th className="px-4 py-2 text-left">Ano</th>
            <th className="px-4 py-2 text-left">Alugado</th>
            <th className="px-4 py-2 text-left">Ações</th>
          </tr>
        </thead>
        <tbody>
          {books.length === 0 ? (
            <tr>
              <td colSpan={6} className="px-4 py-4 text-center">
                Nenhum livro encontrado.
              </td>
            </tr>
          ) : (
            books.map((book) => (
              <tr key={book.id} className="border-t border-gray-200">
                <td className="px-4 py-2">{book.title}</td>
                <td className="px-4 py-2">{book.author}</td>
                <td className="px-4 py-2">{book.gender}</td>
                <td className="px-4 py-2">{book.year}</td>
                <td className="px-4 py-2">{book.isRented ? "Sim" : "Não"}</td>
                <td className="px-4 py-2 space-x-2">
                  <button
                    onClick={() => navigate(`/books/${book.id}`)}
                    className="px-3 py-1 bg-blue-600 hover:bg-blue-700 text-white text-sm rounded"
                  >
                    Ver detalhes
                  </button>
                  <button
                    onClick={() => onDelete(book.id)}
                    className="px-3 py-1 bg-red-600 hover:bg-red-700 text-white text-sm rounded"
                  >
                    Excluir
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default BookTable;
