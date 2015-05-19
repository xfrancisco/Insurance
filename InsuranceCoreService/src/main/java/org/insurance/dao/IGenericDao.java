package org.insurance.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.ResultTransformer;

/**
 * Classe centrale pour l'accès à la base de donnée.
 * Cette classe permet de récupérer et de persister des objets instanciés depuis la base.
 * Se base sur SessionFactoryManager pour récupérer la session courante
 * <ul>
 * <li>Pour récupérer les informations d'une table par sa clé primaire on utilisera {@link #get(Class, Serializable)}
 * <li>Pour récupérer l'ensemble des ligne d'une table on utilisera {@link #getList(Class)}
 * <li>Pour récupérer une ligne ou un sous-ensemble des ligne d'une table filtré par des conditions simple on utilisera {@link #getByCriteria(Class,Condition)} ou {@link #getFirstByCriteria(Class,Condition)}
 * <li>Pour récupérer des valeurs ou un sous-ensemble de valeurs issue d'une requête plus complexe on utilisera {@link #getByCriteria(DetachedCriteria)} ou {@link #getFirstByCriteria(DetachedCriteria)}
 * <li>Pour effectuer des requêtes performantes (souvent utilisés) on utilisera {@link #getByNamedQuery(String, Object...)}
 * <li>Pour effectuer des appels au procédures stockée on passera par la classe DBServiceHelper en héritant de ServiceDBCore dans le package servicedb
 * </ul>
 * Pour éviter de charger la totalité des résultat d'une requête importante en mémoire, on peut utiliser {@link #setMaxResultLimit(int)} pour limiter le nombre d'enregistrements retournés (désactivé par défaut).
 * L'ensemble des exceptions sql/hibernate sont catchées et converties en TechnicalException avec le chaînage vers l'exception d'origine
 * @see DBServiceHelper
 */
public interface IGenericDao {

	/**
	 * A utiliser en vue d'updater une table mappé par un objet.
	 * Retourne un objet par son identifiant unique dans une table mappée.
	 * Si la ligne correspondante n'existe pas la valeur null est retournée.
	 * Pour optimiser les accès en base, l'objet retourné peut provenir du cache hibernate,
	 * pour avoir la possibilité de recharger la dernière version de l'objet on peux utiliser la méthode {@link #get(Class, Serializable, boolean)}
	 * Contraintement get, cette méthode ne détache pas le bean, les modifications apporté à l'objet retournée seront donc persitée en base.
	 * @param type Class de la table mappée
	 * @param id Clé primaire de l'objet
	 * @return instance initialisé à partir d'une ligne ou null si aucune valeur trouvée
	 * @see #get(Class, Serializable, boolean)
	 * @see #getForUpdate(Class, Serializable)
	 */
	/*@Nullable*/
	<T> T getForUpdate(Class<T> type, Serializable id);

	/**
	 * Retourne un objet par son identifiant unique dans une table mappée.
	 * Si la ligne correspondante n'existe pas la valeur null est retournée.
	 * Pour optimiser les accès en base, l'objet retourné peut provenir du cache hibernate,
	 * pour avoir la possibilité de recharger la dernière version de l'objet on peux utiliser la méthode {@link #get(Class, Serializable, boolean)}
	 * <b>L'objet retourné est détaché de la base, il peut donc être considéré comme un dto et remonter les couches</b> 
	 * @param type Class de la table mappée
	 * @param id Clé primaire de l'objet
	 * @return instance initialisé à partir d'une ligne ou null si aucune valeur trouvée
	 * @see #get(Class, Serializable, boolean)
	 * @see #getForUpdate(Class, Serializable)
	 */
	/*@Nullable*/
	<T> T get(Class<T> type, Serializable id);

	/**
	 * Idem que {@link #get(Class, Serializable)} mais avec la possiblité de recharger l'objet pour avoir sa dernière version.
	 * @param type Class de la table mappée
	 * @param id Clé primaire de l'objet
	 * @param reload true si l'objet doit être rechargé
	 * @return instance initialisé à partir d'une ligne ou null si aucune valeur trouvée
	 * @see #get(Class, Serializable)
	 */
	/*@Nullable*/
	<T> T get(Class<T> type, Serializable id, boolean reload);

	/**
	 * Retourne l'ensemble des informations contenus dans une table  (dans la limite de nombre d'enregistrements définit {@link #setMaxResultLimit(int)})
	 * @param type Class de la table mappée
	 * @return Liste vide si la table est vide, l'ensemble des lignes sinon
	 * @see Condition
	 */
	<T> List<T> getList(Class<T> type);

	//	/**
	//	 * Limite le nombre d'enregistrements retournés.
	//	 * Par défaut il n'y a pas de limite appliquée.
	//	 * Une valeur négative ou null désactive cette fonctionnalité.
	//	 * Cette limite est utilisée par les méthode retournant des listes: {@link #getList(Class)}, {@link #getByCriteria(Class, Condition)}, {@link #getByCriteria(DetachedCriteria)} et {@link #getByNamedQuery(String, Object...)}
	//	 * @param maxResultLimit entier>0 représentant le nombre maximal d'enregistrement à retourner 
	//	 */
	//	void setMaxResultLimit(int maxResultLimit);

	/**
	 * Retourne le premier enregistrement d'une requête de filtrage, cf {@link Condition} pour des exemples d'utilisation
	 * @param type Class de la table mappée
	 * @param condition condition de filtrage {@link Condition}, si null pas de filtrage
	 * @return null si aucun enregistrement ne correspond, l'objet instancié sinon
	 * @see Condition
	 * @see #getByCriteria(Class, Condition)
	 */
	/*@Nullable*/
	<T> T getFirstByCriteria(Class<T> type, Condition condition);

	/**
	 * Retourne l'ensemble des enregistrements (dans la limite de nombre d'enregistrements définit {@link #setMaxResultLimit(int)})
	 * d'une requête de filtrage, cf {@link Condition} pour des exemples d'utilisation
	 * @param type Class de la table mappée
	 * @param condition condition de filtrage {@link Condition}, si null pas de filtrage
	 * @return Liste vide si aucun enregistrement ne correspond, l'ensemble des lignes sinon
	 * @see Condition
	 * @see #setMaxResultLimit(int)
	 * @see #getFirstByCriteria(Class, Condition)
	 */
	<T> List<T> getByCriteria(Class<T> type, Condition condition);

	void saveOrUpdate(Object objectToSaveOrUpdate);

	Object save(Object objectToSave);

	void delete(Object objectToDelete);

	/**
	 * Retourne l'ensemble des enregistrements (dans la limite de nombre d'enregistrements définit {@link #setMaxResultLimit(int)})
	 * d'une requête complexe (jointure, order/group by, retriction dans select, aggrégation) définit par un DetachedCriteria hibernate.
	 * Cf {@link #getFirstByCriteria(DetachedCriteria)} pour un exemple d'utilisation
	 * @param criteria critère hibernate
	 * @return Liste vide si aucun enregistrement ne correspond, l'ensemble des lignes sinon
	 * @see #setMaxResultLimit(int)
	 * @see #getFirstByCriteria(DetachedCriteria)
	 */
	<T> List<T> getByCriteria(DetachedCriteria criteria);

	/**
	 * Retourne le premier enregistrement d'une requête complexe (jointure, order/group by, retriction dans select, aggrégation) définit par un DetachedCriteria hibernate.
	 * </br><b>Exemple d'utilisation:</b></br>
	 * <pre>
	 * {@code
	 * //Identique à "select max(finabo) from abo_reahist where numabo = :numabo and numabont = :numabont"
	 * //en considérant que SubscriptionBean représente le mapping hibernate de abo_reahist 
	 * 
	 * final DetachedCriteria criteria = DetachedCriteria.forClass(SubscriptionBean.class);
	 * criteria.add(Restrictions.eq("numabo", numabo)).add(Restrictions.eq("numabont", numabont)).setProjection(Projections.max("finabo"));
	 * final Date finabo = genericDao.getFirstByCriteria(criteria);
	 * }</pre>
	 * @param criteria critère hibernate
	 * @return null si aucun résultat, objet du type de résultat sinon
	 */
	/*@Nullable*/
	<T> T getFirstByCriteria(DetachedCriteria criteria);

	/**
	 * Exécute une requête nommée et retourne le résultat (dans la limite de nombre d'enregistrements définit {@link #setMaxResultLimit(int)})
	 * @param namedQuery nom de la requête nommée, elle doit être définit dans un fichier de mapping hibernate
	 * @param params Paramètres éventuelles de la requêtes à binder
	 * @return Liste vide si aucun enregistrement ne correspond, l'ensemble des lignes sinon
	 * @see #setMaxResultLimit
	 */
	<T> List<T> getByNamedQuery(final String namedQuery, final Object... params);

	<T> List<T> getByNamedHQLQuery(final String namedQuery, final Map<String, Object> hqlParams);

	/**
	 * lister les objets de la classe {@link clazz} en utilisant le HSQL avec les parametres nommes.
	 * Attention : les parametres qui participent à {@link whereCondition} doivent avoir les mêmes valeurs que les noms des attributs de la class (HBM) clazz;
	 * @param clazz
	 * @param whereCondition (numliemouvmt=:numliemouvmtName and nummouvmt between :debutName and nvl(:finName,nummouvmt);
	 * @param paramsKeyAndValue
	 * @return
	 */
	<T> List<T> getByQuery(final Class<T> clazz, final String whereCondition, final Map<String, Object> paramsKeyAndValue);

	<T> List<T> getPaginatedList(Class<T> type, int firstResult, int maxResultLimit);

	<T> List<T> getPaginatedListByCriteria(Class<T> type, Condition condition, int firstResult, int maxResultLimit);

	<T> List<T> getPaginatedListByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResultLimit);

	<T> List<T> getPaginatedListByNamedQuery(String namedQuery, int firstResult, int maxResultLimit, Object[] params);

	<T> List<T> getListByHQLQuery(String hqlQuery, Object... params);

	<T> List<T> getListByHQLQuery(final String hqlQuery, final Map<String, Object> hqlParams);

	<T> List<T> getListByHQLQuery(final String hqlQuery, final ResultTransformer resultTransformer, final Object... params);

	<T> T getByHQLQuery(String hqlQuery);

	<T> T getByHQLQuery(String hqlQuery, Object... params);

	/**
	 * Execute an update or delete for many records. 
	 * @param hqlQuery
	 * @param params
	 * @return
	 */
	void executeUpdateByHQLQuery(String hqlQuery, Object... params);

	<T> T getSingleByQuery(final Class<T> clazz, final String whereCondition, final Map<String, Object> paramsKeyAndValue);

	<T> List<T> getByCriteriaWhithOrder(Class<T> type, Condition condition, Order order);

	Object merge(Object objectToMerge);

	Session getSession();
}
