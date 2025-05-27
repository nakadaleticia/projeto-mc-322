
    public class Desconhecido implements Entidade {
        private final int x=-1;
        private final int y=-1;
        private final int z=-1;
        private final String detalhes; // Para guardar informações sobre por que é desconhecido


        public Desconhecido(String detalhes) {

            this.detalhes = detalhes;
        }

        // Implementação dos métodos da interfac Entidade

        @Override
        public int getX() {
            return x; // Retorna a coordenada X nominal
        }

        @Override
        public int getY() {
            return y; // Retorna a coordenada Y nominal
        }

        @Override
        public int getZ() {
            return z; // Retorna a coordenada Z nominal
        }

        @Override
        public TipoEntidade getTipo() {
            return TipoEntidade.DESCONHECIDO; // Identifica-se como DESCONHECIDO
        }

        @Override
        public String getDescricao() {
            // Fornece uma descrição útil, incluindo os detalhes
            return "Entidade Desconhecida na posição (" + x + "," + y + "," + z + "). Detalhes: " + detalhes;
        }

        @Override
        public char getRepresentacao() {
            return '?'; // Um caractere visual padrão para "desconhecido"
        }

        // Métodos específicos (opcionais) para Desconhecido, se necessário
        public String getDetalhes() {
            return detalhes;
        }
    }

