import React from "react";

interface HeaderProps {
  filters: {
    title: string;
    author: string;
    gender: string;
    year: string;
  };
  setFilters: React.Dispatch<
    React.SetStateAction<{
      title: string;
      author: string;
      gender: string;
      year: string;
    }>
  >;
  onFilter: () => void;
  onAddBook: () => void;
}

const Header: React.FC<HeaderProps> = ({
  filters,
  setFilters,
  onFilter,
  onAddBook,
}) => {
  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  return (
    <div className="flex flex-wrap items-center gap-2 mb-6">
      <input
        type="text"
        placeholder="Título"
        value={filters.title}
        onChange={(e) => setFilters({ ...filters, title: e.target.value })}
        className="w-36 px-3 py-1 border border-gray-300 rounded"
      />
      <input
        type="text"
        placeholder="Autor"
        value={filters.author}
        onChange={(e) => setFilters({ ...filters, author: e.target.value })}
        className="w-36 px-3 py-1 border border-gray-300 rounded"
      />
      <input
        type="text"
        placeholder="Gênero"
        value={filters.gender}
        onChange={(e) => setFilters({ ...filters, gender: e.target.value })}
        className="w-36 px-3 py-1 border border-gray-300 rounded"
      />
      <input
        type="text"
        placeholder="Ano"
        value={filters.year}
        onChange={(e) => setFilters({ ...filters, year: e.target.value })}
        className="w-24 px-3 py-1 border border-gray-300 rounded"
      />

      <button
        onClick={onFilter}
        className="bg-blue-600 text-white px-4 py-1 rounded hover:bg-blue-700"
      >
        Filtrar
      </button>

      <div className="ml-auto flex gap-2">
        <button
          onClick={onAddBook}
          className="bg-green-600 text-white px-4 py-1 rounded hover:bg-green-700"
        >
          Adicionar Livro
        </button>
        <button
          onClick={handleLogout}
          className="bg-red-600 text-white px-4 py-1 rounded hover:bg-red-700"
        >
          Sair
        </button>
      </div>
    </div>
  );
};

export default Header;
