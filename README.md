# Alura Threads

## Threads em java 1: Programação paralela

jconsole

//tarefa deve implementar um runnable
Thread thread = new Thread(tarefa); 
thread.start();


bloco syncronized para criar chave e nao permitir executar bloco em threads
O synchronized significa que bloqueamos o objeto para outros threads;
A sincronização é feita através de mutex, que nada mais é do que a chave do objeto.
pode usar o private Lock lock = new ReentrantLock();
lock.lock() ... lock;unlock();
pode colocar syncronized no metodo , se todo o metodo precisa ser sincronizesd

ArrayliSt nao é thread-safe
opções thread-safe:
List<String> lista = Collections.synchronizedList(new ArrayList<>());
List<String> lista = new Vector<>();
Map mapaThreadSafe = new ConcurrentHashMap();
Set conjunto = Collections.synchronizedSet(new HashSet());

this.wait()
this.notify()
use wait na thread para esperar até receber notify da outra thread e continuar

setDeamon para criar uma thread dependende de outras

---

## Threads em Java 2: Programação concorrente avançada
ExecutorService poolDeThreads = Executors.newCachedThreadPool();

poolDeThreads.execute(new DistribuirTarefa(socket));

pool.scheduleAtFixedRate(tarefa, 0, 60, TimeUnit.MINUTES); //executamos uma tarefa a cada 60 minutos
Runtime runtime = Runtime.getRuntime();
int qtdProcessadores = runtime.availableProcessors();

thread.join() //faz thread main esperar thread finalizar para poder encerrar tb


Threads possuem um cache.
Esse cache faz com que nem sempre todas as variáveis serão vistas e atualizadas de maneira atômica.
A palavra chave volatile evita o uso desse cache e faz que as threads sempre acessem a memória principal.
Como alternativa, podemos utilizar as classes do pacote java.util.concurrent.atomic
Vimos a classe AtomicBoolean como alternativa ao uso do volatile

para tratar excecao na thread adiconar setUncaughtExceptionHandler

usar o callble se quiser q thread retorne resultado. pode criar nova thread para juntar resultados recebidos (o get do callable é blocante)

Quando precisamos uma fila que é compartilhada por threads, devemos usar uma implementação da interface BlockingQueue.
Há vários implementações da interface BlockingQueue.
ArrayBlockingQueue possui um limite de elementos.
A interface BlockingQueue possui métodos que travam a thread, como take() e put().