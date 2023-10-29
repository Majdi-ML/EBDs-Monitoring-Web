<?php

namespace App\Form;

use App\Entity\LogFilesPatterns;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class LogFilesPatterns1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('id',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('nRef',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('ref',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('etat', ChoiceType::class, [
                'attr' => [
                    'class' => 'form-select',
                ],
                'choices'  => [
                'Nouveau' => "Nouveau" ,
                'Supprimé'=>"Supprimé",
                'Modifié' => "Modifié",
                'Inchangé' => "Inchangé" ,
                
            ],
            ])
            ->add('signification',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('identification',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('criticite', ChoiceType::class, [
                'attr' => [
                    'class' => 'form-select',
                ],
                'choices'  => [
                'Critique' => "Critique" ,
                'Majeure'=>"Majeure",
                'Normale' => "Normale",
                
            ],
            ])
            ->add('messageAlarme',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('instructions',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('performAction',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('acquittement',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('complementsInformations',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('refService',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('objet',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
           
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => LogFilesPatterns::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
