export type Student = {
    id?: number;            // O ? indica que o campo é opcional (para quando criamos um novo estudante)
    name: string;          // Nome do estudante
    email:string
    password:string
    phone:string
    studentImage?: string; // Caminho para a imagem do estudante (opcional)
    birth: string;         // Data de nascimento no formato ISO (YYYY-MM-DD)
    mother: Guardian;      // Dados da mãe
    father: Guardian;      // Dados do pai
    absences?: Absence[];  // Lista de faltas (opcional)
}

export interface Guardian {
    id?: number;          // ID do responsável (opcional)
    name: string;         // Nome do responsável
    phone: string;        // Telefone do responsável
}

export interface Absence {
    id?: number;          // ID da falta (opcional)
    date: string;         // Data da falta
    justification?: string; // Justificativa da falta (opcional)
}