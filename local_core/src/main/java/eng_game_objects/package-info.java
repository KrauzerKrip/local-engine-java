/**
 * 
 */
/**
 * @author Andy Darson
 * @apiNote
 *
 *<h1>Type of objects: </h1>
 *<p></p>
 *<table border="1">
 *<tr> <th>class/behavior</th> <th>Graphics</th> <th>Physics</th> <th>Script</th> </tr>
 *<tr> <td>IObject</td> <td>-</td> <td>-</td> <td>-</td> </tr>
 *<tr> <td>IGraphicsObject</td> <td>+</td> <td>-</td> <td>-</td> </tr>
 *<tr> <td>Object</td> <td>-</td> <td>-</td> <td>-</td> </tr>
 *<tr> <td>Primitive</td> <td>+-</td> <td>-</td> <td>-</td> </tr>
 *<tr> <td>Entity</td> <td>-</td> <td>++</td> <td>+</td> </tr>
 *<tr> <td>Trigger</td> <td>-</td> <td>+</td> <td>+</td> </tr>
 *<tr> <td>Model</td> <td>+</td> <td>++</td> <td>+</td> </tr>
 *</table>
 *
 *<p></p>
 *<p></p>
 *
 *<p>
 *<ul>
 *<li>"-" - may not be at all;</li>
 *<li>"+-" - reduced (for graphics: no texture);</li>
 *<li>"+" - maybe or may not to be;</li>
 *<li>"++" - maybe advanced type.</li>
 *</ul>
 *</p>
 */
package eng_game_objects;