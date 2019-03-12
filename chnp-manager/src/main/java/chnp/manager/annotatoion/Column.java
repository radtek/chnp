package chnp.manager.annotatoion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**<p>字段注解</p>
 *
 * @author chngzhen@outlook.com
 * @date 2019-02-22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {

	/**<p>字段所属的字典名</p>
	 * <p>
	 *     用于翻译被注解字段。例如，language:1 -> Java。
	 *
	 *     译文存放在{@code *Name}字段中。如：languageName=Java。
	 * </p>
	 *
	 */
	String dataType() default "";

	/**<p>字段是否可见</p>
	 * <p>
	 *     在向前端返回数据之前，隐藏敏感字段。
	 * </p>
	 *
	 */
	boolean display() default true;

}