/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.model.PetType;
import lombok.Synchronized;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
public class PetTypeToPetTypeCommand implements Converter<PetType, PetTypeCommand> {
	
	@Synchronized
	@Nullable
	@Override
	public PetTypeCommand convert(PetType source) {
		if (source == null) {
			return null;
		}
		
		final PetTypeCommand result = new PetTypeCommand();
		result.setId(source.getId());
		result.setName(source.getName());
		
		return result;
	}	
}
