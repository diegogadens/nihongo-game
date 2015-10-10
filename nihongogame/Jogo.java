package nihongogame;

import java.util.HashMap; //Armazenar imagens e respectivos valores em Romaji
import java.awt.BorderLayout; //Manter a posiçao das imagens e da caixa de texto na tela
import java.awt.FlowLayout;
import javax.swing.ImageIcon; //Carrega imagem
import javax.swing.JFrame;  //Janela base
import javax.swing.JLabel;  //Rotulo para exibir componentes na Janela
import javax.swing.JOptionPane; //temporario
import java.util.Random;    //:rolleyes:
import javax.swing.JTextField; //Caixa de texto
import java.awt.event.ActionListener; //Subclasse para capturar evento
import java.awt.event.ActionEvent;  // """
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset; 

public class Jogo
{
    HashMap<Integer,String> apresentação = null;
    private HashMap<Integer,String> animais;
    private HashMap<Integer,String> hiragana;
    private HashMap<Integer,String> cores;
    private HashMap<Integer,String> frutas;
    private HashMap<Integer,String> verduras;
    /**
     * Variavel para conter a imagem atual
     */
    private ImageIcon img; 
    /**
     * Rotulo para adicionar a imagem
     */
    private JLabel label;
    /**
     * Caixa de texto para entrada do usuario
     */
    private JTextField caixaDeTexto;
    /**
     * Numero aleatorio
     */
    private int numero;
   
    private int acertos = 0, erros = 0, imagem = 1;
    
    private JFrame telaPrincipal;
    
    private TextFieldHandler textHandler;
    
    private ButtonHandler buttonHandler;
    
    private JButton botaoHiragana, botaoKatakana, botaoAnimais, botaoFrutas, botaoProfissões, botaoPronomes, 
            botaoPtesCorpo, botaoCumprimentos, botaoDiasEMeses, botaoPaises, botaoCores, botaoVerduras,
            botaoInterjeições, botaoObjetos, botaoAdjetivos, botaoVoltar, botaoVerTodos, botaoDesempenho;
        
    private int qtdeArquivos;
    
    private JPanel painelSuperior, painelInferior, painelJogo;
    
    private String diretório;
            
    /**
     * Construtor da classe Jogo
     */
    public Jogo() 
    {
        telaPrincipal = new JFrame(" Nihongo Game ");
        telaPrincipal.setLayout( new BorderLayout() );
        telaPrincipal.setSize(720,580);
        telaPrincipal.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Este método centraliza os frames na tela do usuário, independente da resolução da tela
        centralizaFrame(telaPrincipal);
                  
        iniciaInterfacePrincipal();
    }
    
    public void centralizaFrame(JFrame x)
    {
        java.awt.Dimension tamanhoTela = java.awt.Toolkit.getDefaultToolkit().getScreenSize();  
        int larguraTelaPrincipal = x.getWidth();  
        int alturaTelaPrincipal = x.getHeight();  
        x.setLocation((tamanhoTela.width-larguraTelaPrincipal)/2, (tamanhoTela.height-alturaTelaPrincipal)/2);
    }
    
    public void iniciaInterfacePrincipal()
    {
        Random rand = new Random();
        int num = (rand.nextInt(16) + 1);
        String endereço = "imagens/background/" + num + ".jpg";
        img = new ImageIcon( getClass().getResource( endereço ) );        
        
        label = new JLabel(img);
        telaPrincipal.add( label, BorderLayout.CENTER );
        telaPrincipal.setVisible( true );
    
        
        painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout());
        
        painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout());
        
        
        buttonHandler = new ButtonHandler();
        
        
        //Botoes Superiores
        botaoHiragana = new JButton("Hiragana");
        painelSuperior.add(botaoHiragana);
        botaoHiragana.addActionListener(buttonHandler);
        
        botaoKatakana = new JButton("Katakana");
        painelSuperior.add(botaoKatakana);
        botaoKatakana.addActionListener(buttonHandler);
        
        botaoAnimais = new JButton("Animais");
        painelSuperior.add(botaoAnimais);
        botaoAnimais.addActionListener(buttonHandler);
        
        botaoPronomes = new JButton("Pronomes");
        painelSuperior.add(botaoPronomes);
        botaoPronomes.addActionListener(buttonHandler);
        
        botaoPtesCorpo = new JButton("Ptes Corpo");
        painelSuperior.add(botaoPtesCorpo);
        botaoPtesCorpo.addActionListener(buttonHandler);
        
        botaoPaises = new JButton("Países");
        painelSuperior.add(botaoPaises);
        botaoPaises.addActionListener(buttonHandler);
        
        botaoCores = new JButton("Cores");
        painelSuperior.add(botaoCores);
        botaoCores.addActionListener(buttonHandler);
        
        botaoObjetos = new JButton("Objetos");
        painelSuperior.add(botaoObjetos);
        botaoObjetos.addActionListener(buttonHandler);
        
        
        //Botões Inferiores
                     
        botaoAdjetivos = new JButton("Adjetivos");
        painelInferior.add(botaoAdjetivos);
        botaoAdjetivos.addActionListener(buttonHandler);
        
        botaoVerduras = new JButton("Verduras");
        painelInferior.add(botaoVerduras);
        botaoVerduras.addActionListener(buttonHandler);
               
        botaoInterjeições = new JButton("Interjeições");
        painelInferior.add(botaoInterjeições);
        botaoInterjeições.addActionListener(buttonHandler);
        
        botaoCumprimentos = new JButton("Cumprimentos");
        painelInferior.add(botaoCumprimentos);
        botaoCumprimentos.addActionListener(buttonHandler);
        
        botaoDiasEMeses = new JButton("Dias e Meses");
        painelInferior.add(botaoDiasEMeses);
        botaoDiasEMeses.addActionListener(buttonHandler);
        
        botaoFrutas = new JButton("Frutas");
        painelInferior.add(botaoFrutas);
        botaoFrutas.addActionListener(buttonHandler);
        
        botaoProfissões = new JButton("Profissões");
        painelInferior.add(botaoProfissões);
        botaoProfissões.addActionListener(buttonHandler);
        
        telaPrincipal.add(painelSuperior, BorderLayout.NORTH);
        telaPrincipal.add(painelInferior, BorderLayout.SOUTH);
        
        telaPrincipal.setVisible(true);
    }
    
    public void limpaTelaPrincipal()
    {
        if (label != null)
            telaPrincipal.remove(label);
        if (painelJogo != null)
            telaPrincipal.remove(painelJogo);
        if (caixaDeTexto != null)
            telaPrincipal.remove(caixaDeTexto);
        if (painelSuperior != null)
            telaPrincipal.remove(painelSuperior);
        if (painelInferior != null)
            telaPrincipal.remove(painelInferior);
        
        telaPrincipal.repaint();
        telaPrincipal.setVisible(true);
    }
    
    public void iniciaInterfaceJogo()
    {
        limpaTelaPrincipal();
        
        caixaDeTexto = new JTextField( 20 );
        textHandler = new TextFieldHandler();
        caixaDeTexto.addActionListener( textHandler );     
        
        telaPrincipal.add( caixaDeTexto , BorderLayout.SOUTH );
        telaPrincipal.setVisible( true );
      
        painelJogo = new JPanel();
        painelJogo.setLayout(new FlowLayout());
        
        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(buttonHandler);
        painelJogo.add(botaoVoltar);
        
        botaoVerTodos = new JButton("Apresentar Todos");
        botaoVerTodos.addActionListener(buttonHandler);
        painelJogo.add(botaoVerTodos);
        
        botaoDesempenho = new JButton("Desempenho");
        botaoDesempenho.addActionListener(buttonHandler);
        painelJogo.add(botaoDesempenho);
        
        telaPrincipal.add(painelJogo, BorderLayout.NORTH);
        telaPrincipal.setVisible( true );
    }
    
    /**
     * Método polimórfico. Nova imagem randômica
     * Atualiza a imagem do caracter e exibe no Frame
     */
    public void newSprite(String x)
    {     
        String diretorio = x;
        Random gerador = new Random();
        numero = (gerador.nextInt(qtdeArquivos)+1);
        
        
        String endereço = "imagens/" + diretorio +"/" + numero + ".jpg";
          img = new ImageIcon( getClass().getResource( endereço ) );        
        
        label = new JLabel(img);
        
        telaPrincipal.add( label );
        telaPrincipal.setVisible( true );
    }
    
    /**
     * Método polimórfico. Nova imagem estática (utilizada na apresentação)
     * Atualiza a imagem do caracter e exibe no Frame
     */
    public void newSprite(int x)
    {
        //Este método só é chamado depois que uma categoria foi escolhida
        //portanto pode-se acessar diretamente a variável diretório, visto que
        //ela não estará mais nula.
        
        limpaTelaPrincipal();
                
        String endereço = "imagens/" + diretório +"/" + x + ".jpg";
            img = new ImageIcon( getClass().getResource( endereço ) );   
        
        label = new JLabel(img);
        telaPrincipal.add( label );
        telaPrincipal.setVisible( true );
    }
        
    public void apresentaTodos()
    {
        //painel para os botões da apresentação
        final JPanel painelApresentação = new JPanel();
            painelApresentação.setLayout(new FlowLayout());
                
        imagem = 1;//controla qual imagem será exibida na apresentação  
        
        if (diretório.equals("animais"))
            apresentação = animais;
        else
        if (diretório.equals("hiragana"))
            apresentação = hiragana;
        else
        if (diretório.equals("cores"))
            apresentação = cores;
        else
        if (diretório.equals("frutas"))
            apresentação = frutas;
        if (diretório.equals("verduras"))
            apresentação = verduras;
          
        limpaTelaPrincipal();
        JOptionPane.showMessageDialog(null, "Iniciando apresentação");
        
        final JButton next = new JButton("Próximo");                    
            next.addActionListener( //implementa o metodo actionPerformed
                                   new ActionListener()
                                   {
                                     public void actionPerformed(ActionEvent ae)
                                     {
                                            if(imagem<qtdeArquivos)
                                            {
                                                imagem++;
                                                newSprite(imagem);
                                                caixaDeTexto.setText(apresentação.get(imagem));
                                                telaPrincipal.add(caixaDeTexto, BorderLayout.SOUTH);
                                            }
                                            else
                                                JOptionPane.showMessageDialog(null, "Este é o último item");
                                      }
                                   } 
                                  );         
                
         final JButton prev = new JButton("Anterior");                    
            prev.addActionListener( //implementa o metodo actionPerformed
                                   new ActionListener()
                                   {
                                     public void actionPerformed(ActionEvent ae)
                                     {
                                            if(imagem>1)
                                            {
                                                imagem--;
                                                newSprite(imagem);
                                                caixaDeTexto.setText(apresentação.get(imagem));
                                                telaPrincipal.add(caixaDeTexto, BorderLayout.SOUTH);
                                            }
                                            else
                                                JOptionPane.showMessageDialog(null, "Este é primeiro item");
                                     }
                                   }                              
                                  );         
                                  
         final JButton encerrar = new JButton("Encerrar Apresentação");                    
            encerrar.addActionListener( //implementa o metodo actionPerformed
                                   new ActionListener()
                                   {
                                     public void actionPerformed(ActionEvent ae)
                                     {
                                         telaPrincipal.remove(painelApresentação);
                                         limpaTelaPrincipal();
                                         iniciaInterfaceJogo();
                                         newSprite(diretório);
                                         JOptionPane.showMessageDialog(null, "Jogo reiniciado");
                                     }
                                   }                              
                                  );

         painelApresentação.add(prev);
         painelApresentação.add(next);
         painelApresentação.add(encerrar);
         
         
         telaPrincipal.add(painelApresentação, BorderLayout.NORTH);
         telaPrincipal.setVisible(true);
        
         newSprite(imagem);
         caixaDeTexto.setText(apresentação.get(imagem));
         telaPrincipal.add(caixaDeTexto, BorderLayout.SOUTH);
    }
    
    
        
        /*
     * Inicializa o HashMap e adiciona as chaves e seus respectivos valores.
     */
    public void initHashMapAnimais()
    {
        animais = new HashMap<Integer,String>();
        animais.put(1, "niwatori");
        animais.put(2, "zoo");
        animais.put(3, "usagi");
        animais.put(4, "saru");
        animais.put(5, "hakuchyoo");
        animais.put(6, "ushi");
        animais.put(7, "kirin");
        animais.put(8, "inu");
        animais.put(9, "neko");
        animais.put(10, "ariru");
        animais.put(11, "uma");
        animais.put(12, "rakuda");
        animais.put(13, "sai");
        animais.put(14, "wani");
        animais.put(15, "shimauma");
        animais.put(16, "kuma");
        animais.put(17, "arikui");
        animais.put(18, "tora");
        animais.put(19, "ebi");
        animais.put(20, "oobashi");
        animais.put(21, "sasori");
        animais.put(22, "sakana");
        animais.put(23, "kingyo");
        animais.put(24, "kitsune");
        animais.put(25, "hachi");
        animais.put(26, "kujaku");
        animais.put(27, "fukuroo");
        animais.put(28, "tonakay");
        animais.put(29, "hyoo");
        animais.put(30, "batta");
        animais.put(31, "dani");
        animais.put(32, "manmos");
        animais.put(33, "raion");
        animais.put(34, "mesuraion");
        animais.put(35, "mesuyagi");
        animais.put(36, "kame");
        animais.put(37, "kamareon");
        animais.put(38, "ari");
        animais.put(39, "kujira");
        animais.put(40, "kaero");
        animais.put(41, "inoshishi");
        animais.put(42, "taka");
        animais.put(43, "buta");
    }
    
    public void initHashMapHiragana()
    {
        hiragana = new HashMap<Integer,String>();
        hiragana.put(1, "a");
        hiragana.put(2, "ba");
        hiragana.put(3, "be");
        hiragana.put(4, "bi");
        hiragana.put(5, "bo");
        hiragana.put(6, "bu");
        hiragana.put(7, "bya");
        hiragana.put(8, "byo");
        hiragana.put(9, "byu");
        hiragana.put(10, "cha");
        hiragana.put(11, "chi");
        hiragana.put(12, "cho");
        hiragana.put(13, "chu");
        hiragana.put(14, "da");
        hiragana.put(15, "de");
        hiragana.put(16, "ji");
        hiragana.put(17, "do");
        hiragana.put(18, "ja ");
        hiragana.put(19, "jo");
        hiragana.put(20, "ju");
        hiragana.put(21, "e");
        hiragana.put(22, "fu");
        hiragana.put(23, "ga");
        hiragana.put(24, "ge");
        hiragana.put(25, "gi");
        hiragana.put(26, "go");
        hiragana.put(27, "gu");
        hiragana.put(28, "gya");
        hiragana.put(29, "gyo");
        hiragana.put(30, "gyu");
        hiragana.put(31, "ha");
        hiragana.put(32, "he");
        hiragana.put(33, "hi");
        hiragana.put(34, "ho");
        hiragana.put(35, "hya");
        hiragana.put(36, "hyo");
        hiragana.put(37, "hyu");
        hiragana.put(38, "i");
        hiragana.put(39, "ja");
        hiragana.put(40, "ji");
        hiragana.put(41, "jo");
        hiragana.put(42, "ju");
        hiragana.put(43, "ka");
        hiragana.put(44, "ke");
        hiragana.put(45, "ki");
        hiragana.put(46, "ko");
        hiragana.put(47, "ku");
        hiragana.put(48, "kya");
        hiragana.put(49, "kyo");
        hiragana.put(50, "kyu");
        hiragana.put(51, "ma");
        hiragana.put(52, "me");
        hiragana.put(53, "mi");
        hiragana.put(54, "mo");
        hiragana.put(55, "mu");
        hiragana.put(56, "mya");
        hiragana.put(57, "myo");
        hiragana.put(58, "myu");
        hiragana.put(59, "n");
        hiragana.put(60, "na");
        hiragana.put(61, "ne");
        hiragana.put(62, "ni");
        hiragana.put(63, "no");
        hiragana.put(64, "nu");
        hiragana.put(65, "nya");
        hiragana.put(66, "nyo");
        hiragana.put(67, "nyu");
        hiragana.put(68, "o");
        hiragana.put(69, "pa");
        hiragana.put(70, "pe");
        hiragana.put(71, "pi");
        hiragana.put(72, "po");
        hiragana.put(73, "pu");
        hiragana.put(74, "pya");
        hiragana.put(75, "pyo");
        hiragana.put(76, "pyu");
        hiragana.put(77, "ra");
        hiragana.put(78, "re");
        hiragana.put(79, "ri");
        hiragana.put(80, "ro");
        hiragana.put(81, "ru");
        hiragana.put(82, "rya");
        hiragana.put(83, "ryo");
        hiragana.put(84, "ryu");
        hiragana.put(85, "sa");
        hiragana.put(86, "se");
        hiragana.put(87, "sha");
        hiragana.put(88, "shi");
        hiragana.put(89, "sho");
        hiragana.put(90, "shu");
        hiragana.put(91, "so");
        hiragana.put(92, "su");
        hiragana.put(93, "ta");
        hiragana.put(94, "te");
        hiragana.put(95, "to");
        hiragana.put(96, "tsu");
        hiragana.put(97, "zu");
        hiragana.put(98, "u");
        hiragana.put(99, "wa");
        hiragana.put(100, "we");
        hiragana.put(101, "wi");
        hiragana.put(102, "wo");
        hiragana.put(103, "ya");
        hiragana.put(104, "yo");
        hiragana.put(105, "yu");
        hiragana.put(106, "za");
        hiragana.put(107, "ze");
        hiragana.put(108, "zo");
        hiragana.put(109, "zu"); 
        hiragana.put(110, "zu"); 
    }
    
    public void initHashMapFrutas()
    {
        frutas = new HashMap<Integer,String>();
        frutas.put(1, "remon");
        frutas.put(2, "banana");
        frutas.put(3, "ichigo");
        frutas.put(4, "meron");
        frutas.put(5, "mikan");
        frutas.put(6, "guaba");
        frutas.put(7, "suika");
        frutas.put(8, "zakuro");
        frutas.put(9, "papaya no kajitsu");
        frutas.put(10, "painappuru");
        frutas.put(11, "budoo");
        frutas.put(12, "ringo");
        frutas.put(13, "nashi");
        frutas.put(14, "momo");
    }
           
    public void initHashMapCores()
    {
        cores = new HashMap<Integer,String>();
        cores.put(1, "hai");
        cores.put(2, "aoi");
        cores.put(3, "mizu iro");
        cores.put(4, "asagi");
        cores.put(5, "chairo");
        cores.put(6, "midori iro");
        cores.put(7, "akai");
        cores.put(8, "kuroi");
        cores.put(9, "ki");
        cores.put(10, "momo iro");
        cores.put(11, "orenji iro");
        cores.put(12, "daidai iro");
        cores.put(13, "murasaki");
        cores.put(14, "shiroi");
    }

    public void initHashMapVerduras()
    {
        verduras = new HashMap<Integer,String>();
        verduras.put(1, "do");
        verduras.put(2, "re");
        verduras.put(3, "mi");
        verduras.put(4, "fa");
        verduras.put(5, "sol");
        verduras.put(6, "la");
        verduras.put(7, "si");

    }
   
    /**
     * Verifica se a resposta está correta.
     * @param numero Numero aleatorio gerado.
     * @param string Resposta do usuario
     * @param dir Diretório que esteja sendo utilizado no momento
     */
    public void verifica(int numero,String stringInserida, String dir)
    {
        String valor = "";
        
        if (dir.equals("animais"))
                valor = animais.get(this.numero);
        else
        if (dir.equals("hiragana"))
                valor = hiragana.get(this.numero);
        else
        if (dir.equals("cores"))
                valor = cores.get(this.numero);
        else
        if (dir.equals("frutas"))
                valor = frutas.get(this.numero);
        else
        if (dir.equals("verduras"))
                valor = verduras.get(this.numero);
        
        
        if(valor.equals(stringInserida))
        {
            //JOptionPane.showMessageDialog( null, "Voce Acertou!!!" );
            acertos++;
        }
        else
        {
           JOptionPane.showMessageDialog( null, "Voce Errou!! \nResposta correta: " + valor ); 
           erros++;
        }
    }
    
    
    /*
     * Gera um gráfico do tipo pizza, 3D, com a quantidade e porcentagem
     * de erros e acertos do usuário, mostra este gráfico em uma janela separada.
     */
    public void mostraDesempenho()
    {
        if((acertos+erros)>0)
        {
            JFrame janelaDesempenho = new JFrame("");
            janelaDesempenho.setLayout(new BorderLayout());
            janelaDesempenho.setSize(370,386);
            
            centralizaFrame(janelaDesempenho);
        
            //valores possíveis para o gráfico
            DefaultPieDataset dados = new DefaultPieDataset();
            dados.setValue("Erros: "+erros, erros);
            dados.setValue("Acertos: "+acertos, acertos);
        
            JFreeChart grafico;
        
            if(diretório.equals("ptescorpo"))
                grafico = ChartFactory.createPieChart3D("Desempenho na categoria partes do corpo:", dados, true,false, false);
            else
            if(diretório.equals("diasemeses"))
                grafico = ChartFactory.createPieChart3D("Desempenho na categoria dias e mêses:", dados, true,false, false);
            else
                grafico = ChartFactory.createPieChart3D("Desempenho na categoria "+diretório+":", dados, true,false, false);
            
        //Trabalha na imagem do gráfico pra deixá-la mais apresentável
            PiePlot plotagem = (PiePlot) grafico.getPlot();  
            plotagem.setLabelGenerator(new StandardPieSectionLabelGenerator(  
                 "{0} ({2})"));//define porcentagem no gráfico  
            plotagem.setForegroundAlpha(new Float(0.6));//define a transparencia do grafico
            plotagem.setCircular(true);//deixa o gráfico como um circulo perfeito
         
             //transforma o grafico gerado em uma imagem para exibir no frame
            ImageIcon imagemGrafico = new ImageIcon( grafico.createBufferedImage(350,350) );        
            JLabel labelGrafico = new JLabel(imagemGrafico);
        
            janelaDesempenho.add(labelGrafico, BorderLayout.CENTER);
            janelaDesempenho.setVisible(true);
            
            
            double relacao = (acertos*1.0);
            double soma = ((acertos + erros)*1.0);
            
            relacao = (relacao/soma);
            
            if((relacao>0.8) && soma >= 30)
                JOptionPane.showMessageDialog(null, "Parabéns, você teve um ótimo desempenho");
        }
        else
          JOptionPane.showMessageDialog(null, "Seu desempenho ainda está zerado");
    }
    
    
    private class TextFieldHandler implements ActionListener
   {
      // processa eventos de campo de texto
      public void actionPerformed( ActionEvent event )
      {
         String stringInserida = ""; // declara string a ser exibida

         // usuário pressionou Enter no JTextField 
         if (event.getSource() == caixaDeTexto )
         {
            stringInserida = (String.format(event.getActionCommand())).toLowerCase(); //Recebe o conteudo da caixa de texto
            verifica(numero,stringInserida, diretório);
            caixaDeTexto.setText("");
            telaPrincipal.remove( label );
            newSprite(diretório);
         } 
      } // fim do método actionPerformed
   } // fim da classe TextFieldHandler interna private
    
    
   private class ButtonHandler implements ActionListener
   {
      // processa eventos de campo de texto
      public void actionPerformed( ActionEvent event )
      {
         if (event.getSource() == botaoAnimais )
         {
             diretório = "animais";
             qtdeArquivos = 43;
             initHashMapAnimais();
             iniciaInterfaceJogo();
             newSprite(diretório);
             caixaDeTexto.setText("Insira aqui o nome do animal apresentado");
         }
         else
         if (event.getSource() == botaoFrutas )
         {  
             diretório = "frutas";
             qtdeArquivos = 14;
             initHashMapFrutas();
             iniciaInterfaceJogo();
             newSprite(diretório);
             caixaDeTexto.setText("Insira aqui o nome da fruta apresentada");
         }
         else
         if (event.getSource() == botaoHiragana )
         {  
             diretório = "hiragana";
             qtdeArquivos = 110;
             initHashMapHiragana();
             iniciaInterfaceJogo();
             newSprite(diretório);
             caixaDeTexto.setText("Insira aqui o romaji equivalente ao hiragana apresentado");
         }    
         else
         if (event.getSource() == botaoProfissões )
         {  
             diretório = "profissoes";
             //qtdeArquivos = 0;
             //initHashMapProfissões();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui o nome da profissão apresentada");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoPronomes )
         {  
             diretório = "pronomes";
             //qtdeArquivos = 0;
             //initHashMapPronomes();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui a tradução do pronome apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoPtesCorpo )
         {  
             diretório = "ptescorpo";
             //qtdeArquivos = 0;
             //initHashMapPtesCorpo();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui o nome da parte do corpo apresentada");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoCumprimentos )
         {  
             diretório = "cumprimentos";
             //qtdeArquivos = 0;
             //initHashMapCumprimentos();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui a tradução do cumprimento apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoDiasEMeses )
         {  
             diretório = "diasemeses";
             //qtdeArquivos = 0;
             //initHashMapDiasEMeses();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui a tradução do dia ou mês apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoPaises )
         {  
             diretório = "paises";
             //qtdeArquivos = 0;
             //initHashMapPaises();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui o nome do país apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoCores )
         {  
             diretório = "cores";
             qtdeArquivos = 14;
             initHashMapCores();
             iniciaInterfaceJogo();
             newSprite(diretório);
             caixaDeTexto.setText("Insira aqui o nome da cor apresentada");
             //JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoVerduras )
         {  
             diretório = "verduras";
             qtdeArquivos = 7;
             initHashMapVerduras();
             iniciaInterfaceJogo();
             newSprite(diretório);
             caixaDeTexto.setText("");
         }
         else
         if (event.getSource() == botaoInterjeições )
         {  
             diretório = "interjeicoes";
             //qtdeArquivos = 0;
             //initHashMapInterjeições();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui a tradução da interjeição apresentada");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoObjetos )
         {  
             diretório = "objetos";
             //qtdeArquivos = 0;
             //initHashMapObjetos();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui o nome do objeto apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoKatakana )
         {  
             diretório = "katakana";
             //qtdeArquivos = 0;
             //initHashMapVerbos();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui a tradução do verbo apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoAdjetivos )
         {  
             diretório = "adjetivos";
             //qtdeArquivos = 0;
             //initHashMapAdjetivos();
             //iniciaInterfaceJogo();
             //newSprite(diretório);
             //caixaDeTexto.setText("Insira aqui a tradução do adjetivo apresentado");
             JOptionPane.showMessageDialog(null, "Botão "+diretório);
         }
         else
         if (event.getSource() == botaoVoltar )
         {  
             limpaTelaPrincipal();
             iniciaInterfacePrincipal();
             if((acertos + erros)>0)
                JOptionPane.showMessageDialog(null, "Seu desempenho foi zerado. Escolha outra categoria.");
             acertos = erros = 0;
         }
         else
         if(event.getSource() == botaoVerTodos)
         {
             apresentaTodos();
         }
         else
         if(event.getSource() == botaoDesempenho)
         {
             mostraDesempenho();
         }
         
         
      } // fim do método actionPerformed
   } 
    
}
