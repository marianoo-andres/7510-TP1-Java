package ar.uba.fi.tdd.rulogic.model;

/***
 * Interface to expose just the necessary funcionality of database to the client (App)
 */
public interface IKnowledgeBase {
    /***
     *
     * @param queryString the query to be answered
     * @return true if the element of query is present in database false if not
     * @throws InvalidQueryException in case query is not well formed
     */
    Boolean answer(String queryString) throws InvalidQueryException;
}
