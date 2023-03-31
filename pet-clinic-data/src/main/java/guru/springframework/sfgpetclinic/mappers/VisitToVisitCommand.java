/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.Visit;
import lombok.Synchronized;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Component
public class VisitToVisitCommand implements Converter<Visit, VisitCommand> {

	@Synchronized
	@Nullable
	@Override
	public VisitCommand convert(Visit source) {
		if (source == null) {
			return null;
		}

		final VisitCommand result = new VisitCommand();
		result.setId(source.getId());
		result.setDate(source.getDate());
		result.setDescription(source.getDescription());
		return result;
	}
}
