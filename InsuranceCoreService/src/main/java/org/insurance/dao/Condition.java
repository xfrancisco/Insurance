package org.insurance.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * Cette classe définit un ensemble de critères basés sur hibernate permettant de construire une clause de filtrage.
 * Cette classe s'utilise avec son constructeur statique {@link #condition} auquel on ajoute de nouvelles conditions avec {@link #add}.
 * Hibernate fournit un ensemble de {@link org.hibernate.criterion.Restrictions filtres} pouvant être utilisés pour construire les conditions de filtrage.
 * </br><b>Exemple d'utilisation:</b></br>
 * <pre>
 * {@code
 * //import static pour plus de lisibilité
 * import static fr.canalplus.cgaweb.dao.Condition.condition;
 * import static org.hibernate.criterion.Restrictions.eq;
 * import static org.hibernate.criterion.Restrictions.isNotNull;
 * 
 * ... 
 * //Identique à "select * from table where colA=:valueForA and colB is not null"
 * //en considérant que MappedTableBean représente le mapping hibernate de table 
 * 
 * final List<MappedTableBean> result = genericDao.getByCriteria(MappedTableBean.class,
 *   // Construction de la condition "colA=:valueForA and colB is not null"
 *   condition( eq("colA",valueForA) ).add( isNotNull("colB") )
 *   );
 * }</pre>
 * @author Valérian MOQUAY
 * @see IGenericDao#getByCriteria(Class, Condition)
 * @see IGenericDao#getFirstByCriteria(Class, Condition)
 * @see org.hibernate.criterion.Restrictions
 */
public class Condition implements Iterable<Criterion> {
	private final List<Criterion> criterions;

	private Condition() {
		criterions = new ArrayList<Criterion>();
	}

	/**
	 * Initialise une condition avec le critère de filtrage initial
	 * @param criterion Critère initial
	 * </br><b>Exemple d'utilisation:</b></br>
	 * <pre>
	 * {@code
	 * import static fr.canalplus.cgaweb.dao.Condition.condition;
	 * import static org.hibernate.criterion.Restrictions.eq;
	 * ...
	 * condition(eq("colA",valueForA))
	 * }</pre>
	 * @return Condition initialisée
	 */
	public static final Condition condition(final Criterion criterion) {
		final Condition condition = new Condition();
		return condition.add(criterion);
	}

	/**
	 * Ajoute une condition de filtrage
	 * @param criterion critère supplémentaire de filtrage
	 * @return la condition modifiée
	 */
	public Condition add(final Criterion criterion) {
		criterions.add(criterion);
		return this;
	}

	/**
	 * Permet d'accéder à l'ensemble des critères contenus dans la condition
	 */
	@SuppressWarnings("null")
	@Override
	public Iterator<Criterion> iterator() {
		final Iterator<Criterion> result = criterions.iterator();
		if (result == null) {
			return Collections.emptyIterator();
		} else {
			return result;
		}
	}
}
